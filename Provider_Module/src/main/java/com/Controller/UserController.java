package com.Controller;

import com.Entity.RequestResult;
import com.Entity.User;
import com.Service.FileUploadService;
import com.Service.ServiceImp.FileUploadServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    private final FileUploadServiceImp fileUploadServiceImp;
    @Autowired
    public UserController(FileUploadServiceImp fileUploadServiceImp) {
        this.fileUploadServiceImp = fileUploadServiceImp;
    }

    @GetMapping("/")
    public String welcome() {
        return "Hello, This is Spring-Cloud-Eureka-Provider 01";
    }

    private Map<String, Object> userResultMap = new HashMap<>();
    private User aUser = new User(1,"Tom",12);

    @GetMapping("/service9020/hello")
    public String helloString() {
        return "Hello, Spring Cloud. provider 001";
    }

    @GetMapping("/queryUser/{userId}")
    public Map<String, Object> queryUser(@PathVariable Integer userId){
        userResultMap.put("Get your user: ", aUser);
        return userResultMap;
    }

    @GetMapping("/sendThisRequestForTest")
    public String setUser() throws ParseException {
       return fileUploadServiceImp.setUser();
    }

    @DeleteMapping("/deleteUser/deleteOneUser")
    public User deleteUser(@RequestParam("userId")    Integer userId) {
        User newUser = new User();
        newUser.setUserId(userId);
        System.out.println(newUser.toString() +"provider 001"+"---" + new Date()+"---" + "/updateUser/putUser");
        return aUser;
    }

    @PostMapping(value = "/requestUpload",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public RequestResult fileUpload(@RequestPart("myFile") MultipartFile file) throws FileNotFoundException {
        return fileUploadServiceImp.fileUpload(file);
    }

//    @RequestMapping(value = "/requestDownload", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
//    public void fileDownload(HttpServletResponse response){
//        fileUploadService.fileDownload(response);
//    }

    @PostMapping(value = "/localFileDownload")
    @ResponseBody
    public ResponseEntity<InputStreamResource> localSererFileDownload(
            @RequestParam(value = "fileName") String fileName) throws FileNotFoundException {
        return fileUploadServiceImp.localSererFileDownload(fileName);
    }

}
