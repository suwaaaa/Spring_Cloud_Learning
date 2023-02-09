package Feign.Service;

import Feign.FallBackHandler.MyFallBackFactory;
import Feign.Entity.RequestResult;
import Feign.Utills.FileRequestConfig;
import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

//使用Feign的客服端注解绑定远程的服务   该远程服务名字可以是大写也可以是小写   当fallback和fallbackFactory同时配置时，生效的为fallback
@FeignClient(name = "SPRING-CLOUD-EUREKA-PROVIDER-01",/*fallback=MyFallBack.class*/ fallbackFactory = MyFallBackFactory.class
,configuration = FileRequestConfig.class)  // 使用Feign实现服务熔断

@Component
public interface RequestService {
    /**MyFallBackFactory.java
     *  服务熔断获取异常信息，拿到远程服务的异常信息
     *  Description:
     *  声明远程服务者提供的方法  比如provider01的helloString方法
     *  声明的方法路径需要是一样的
     *  进而实现该接口
     */
    @RequestMapping("/service9020/hello")
    String helloString();

    @PostMapping(value = "/requestUpload",consumes = MediaType.MULTIPART_FORM_DATA_VALUE )
    RequestResult fileUpload(@RequestPart("myFile") MultipartFile file);

    @RequestMapping(value = "/requestDownload", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
    Response fileDownload();
}
