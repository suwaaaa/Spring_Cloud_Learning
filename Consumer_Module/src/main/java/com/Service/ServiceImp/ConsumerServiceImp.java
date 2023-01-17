package com.Service.ServiceImp;

import com.Service.ConsumerService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;

@Component
public class ConsumerServiceImp implements ConsumerService {

    private final RestTemplate restTemplate;
    @Autowired
    public ConsumerServiceImp(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String welcome() {
        return "Hello, This is Spring-Cloud-Eureka-Consumer 01";
    }

    public String hellString() {
        // String------ResponseEntity<user>------ResponseEntity<map>
        // 由于加入了LoadBalance注解，可以注解只用服务名称
        ResponseEntity<String> responseEntity;
        try {
            responseEntity = restTemplate.getForEntity("http://SPRING-CLOUD-EUREKA-PROVIDER-01/service9020/hello", String.class);
            return "调用远程Spring Cloud的Provider服务(服务器-9020): "  + responseEntity.toString();
        }catch (Exception e){
            return "失败 调用远程Spring Cloud的Provider服务(服务器-9020) [失败]: " + e.getMessage();
        }
    }

    @RequestMapping("/web/hystrix")
   public String hystrix() {
        ResponseEntity<String>  responseEntity;
        try{
            responseEntity = restTemplate
                    .getForEntity("http://SPRING-CLOUD-EUREKA-PROVIDER-01/service9020/hello", String.class);
            System.out.println("This response is: " + responseEntity.toString());
            return "调用远程Spring Cloud的远程服务 "  + responseEntity + "成功 （失败时则为Hystrix服务熔断）";
        }catch (Exception e){
            e.printStackTrace();
            return "调用远程Spring Cloud的远程服务 失败调用 （失败为Hystrix服务熔断）" + e.getMessage();
        }
    }


}
