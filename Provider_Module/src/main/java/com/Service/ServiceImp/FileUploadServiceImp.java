package com.Service.ServiceImp;

import com.Entity.RequestResult;
import com.Entity.User;
import com.Service.FileUploadService;
import com.Utils.FileProperty;
import com.Utils.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.util.annotation.NonNull;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

@Component
//1.@PropertySource({"classpath:application.properties"})
public class FileUploadServiceImp implements FileUploadService {

//    2.@Value("${web.file.path}")
//    3.private String filePath;

    private final FileProperty fileProperty;
    @Autowired
    public FileUploadServiceImp(FileProperty fileProperty) {
        this.fileProperty = fileProperty;
    }

    //文件大小校验 通过为True
    private Boolean fileSizeValidationPass(Long fileSize){
        System.out.println("配置文件大小为：" + fileProperty.getLimitSize()*1024 + "Kb 当前的文件大小为： " + fileSize/1024 + "Kb");
        return fileSize / 1024 <= fileProperty.getLimitSize() * 1024;
    }

    //文件类型校验 通过为True
    private HashMap<Boolean, String> fileTypeValidationPass(String fileType){
        HashMap<Boolean, String> map = new HashMap<>();
        boolean matchConfigType;
        matchConfigType = fileProperty.getAllowTypes().stream().noneMatch(type -> type.contains(fileType));
        System.out.println("getAllowTypes : " + fileProperty.getAllowTypes());
        if (matchConfigType) {
            System.out.println("当前文件类型为 " + fileType + ", 为不可支持上传类型！");
            map.put(false, ResultCode.FILE_REQUEST_NOT_SUPPORT_TYPE.getMessageValue());
            return map;
        }
        map.put(true, ResultCode.FILE_REQUEST_SUPPORT_TYPE_ALLOW.getMessageValue());
        return map;
    }

    //文件路径校验
    private String fileUploadPathInit(String fileTargetPath){
        System.out.println("配置文件路径为 " + fileTargetPath);
        File targetPath = new File(fileTargetPath);
        if (!targetPath.exists()) {
            final boolean mkdirsPath = targetPath.mkdirs();
            return " File Upload Path Initial Finish... Create Path - " + mkdirsPath;
        }
        return " File Upload Path Initial Finish... Create Path - " + false;
    }

    @PostMapping(value = "/requestUpload",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseBody
    @Override
    public RequestResult fileUpload(@RequestPart("myFile") final MultipartFile file) throws FileNotFoundException {
//        request.getAttributeNames()
        RequestResult requestResult = new RequestResult();
        //文件大小校验
        if (!fileSizeValidationPass(file.getSize())) {
            return requestResult.setRequestCode(ResultCode.FILE_REQUEST_SIZE_TOO_LARGE.getErrorCodeKey())
                    .setRequestRequestValue(ResultCode.FILE_REQUEST_SIZE_TOO_LARGE.getMessageValue());
        }
        String fileName = file.getOriginalFilename();// 获得原始文件名
        // 截取文件类型; 这里可以根据文件类型进行判断,比如图片的jpeg,png
        String fileType = fileName.substring(Objects.requireNonNull(fileName).lastIndexOf('.'));
        //文件类型校验
        HashMap fileTypeValidationResult = fileTypeValidationPass(Objects.requireNonNull(fileType));
        if (fileTypeValidationResult.containsKey(false)){
            return requestResult.setRequestCode(ResultCode.FILE_REQUEST_NOT_SUPPORT_TYPE.getErrorCodeKey())
                    .setRequestRequestValue(ResultCode.FILE_REQUEST_NOT_SUPPORT_TYPE.getMessageValue());
        }
        //准备文件处理
        String storeFileName = UUID.randomUUID() + "--" + fileName;
        System.out.println("转换后的名称: " + storeFileName);
        //文件路径校验
        final String filStorePath = ResourceUtils.getURL("classpath:").getPath() + fileProperty.getPath();
//        Paths.get(classas.getClassLoader().getResource(".").toURI()).toString()
        System.out.println(fileUploadPathInit(filStorePath));
        try {
            //指定文件上传目录
            File destTop = new File(filStorePath + storeFileName);//storeFileName  fileName
            file.transferTo(destTop);
            //成功上传文件
            return requestResult.setRequestCode(ResultCode.FILE_UPLOAD_COMPLETED.getErrorCodeKey())
                    .setRequestRequestValue(ResultCode.FILE_UPLOAD_COMPLETED.getMessageValue());
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
            return requestResult.setRequestCode(ResultCode.FIAL_UNKNOWN_RESPONSE.getErrorCodeKey())
                    .setRequestRequestValue(ResultCode.FIAL_UNKNOWN_RESPONSE.getMessageValue() + " \n" + e.toString());
        }
    }


    @Override
    public ResponseEntity<InputStreamResource> localSererFileDownload(String fileName) throws FileNotFoundException {
        final String filStorePath = ResourceUtils.getURL("classpath:").getPath() + fileProperty.getPath();
        String filePath = filStorePath + fileName;
        File targetFile = new File(filePath).getAbsoluteFile();//getAbsoluteFile
        System.out.println("当前下载文件 （获取从目录" + targetFile + ") 下载...");
        FileInputStream fileInputStream = new FileInputStream(targetFile);
        InputStreamResource isr = new InputStreamResource(fileInputStream);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header("Content-disposition", "attachment; filename="+targetFile)
                .body(isr);
    }












    //Javon 测试类测试
    @GetMapping("/sendThisRequestForTest")
    public String setUser() throws ParseException {
        User aUser = new User();
        aUser.setUserName("Poole");
        aUser.setAge(1);
        SimpleDateFormat birthdayDate = new SimpleDateFormat("yyyy-MM-dd");
        Date userBirthday = birthdayDate.parse("2016-6-19");
        aUser.setBirthday(userBirthday);
        System.out.println("you have user: " + aUser.toString());
        return aUser.toString();
    }













}
