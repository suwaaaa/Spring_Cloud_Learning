package Feign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

/*
 * feign client的层次架构是包裹的层次，hystrix控制整个请求从调用到方法返回，而ribbon控制从选址到socket返回
 * */
@SpringBootApplication
@EnableFeignClients            //      开启Feign的客户端  port: 9041
@EnableEurekaClient
@EnableHystrix
//注解@EnableFeignClients(basePackages = {“com.xx.xx.test.service”})：表示Feign 需要扫描的package路径
public class FeignApp {

    public static void main(String[] args) {
        SpringApplication.run( FeignApp.class, args);
    }
}
