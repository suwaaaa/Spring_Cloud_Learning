package Feign.Controller;

import Feign.Service.RequestService;
import Feign.Entity.RequestResult;
import feign.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.atomic.AtomicReference;

@RestController
public class FeignController {
    /*1）Feign接口的方法名必须与inner方法名相同
    （2）uri必须相同
    （3）Feign接口的接口上加上注解@FeignClient(value = XXX)，这个注解标识当前是一个Feign接口的客户端，value属性对应的是服务名称。
    */

    private final RequestService requestService;
    @Autowired
    public FeignController(RequestService requestService) {
        this.requestService = requestService;
    }

    @GetMapping("/service9020/hello")  //  http://localhost:9041/service9020/hello
    public String helloString(){
        return requestService.helloString();
    }

    @PostMapping(value = "/requestUpload",consumes = MediaType.MULTIPART_FORM_DATA_VALUE )
    public RequestResult fileUpload(@RequestPart("myFile") MultipartFile file){
        return requestService.fileUpload(file);
    }

    @GetMapping("/download")//ResponseEntity 用来接受文件流
    public ResponseEntity<byte[]> fileDownload(@RequestParam(value = "fileName") String fileName) {
        ResponseEntity<byte[]> finialResult;
        InputStream inputStream = null;
        System.out.println("Ready to download file");
        try {
            Response getResponse = requestService.fileDownload();
            Response.Body body = getResponse.body();
            inputStream = body.asInputStream();
            byte[] b = new byte[inputStream.available()];
            int readFileResult = inputStream.read(b);
            System.out.println("Read file Complete? (1/0/-1): " + readFileResult);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add(HttpHeaders.CONTENT_DISPOSITION, "attachment;fileName="+ fileName);
            httpHeaders.add(HttpHeaders.CONTENT_TYPE,MediaType.APPLICATION_JSON_VALUE);
            finialResult = new ResponseEntity<>(b, httpHeaders, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            finialResult = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } finally {
            if(inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return finialResult;
    }
}
