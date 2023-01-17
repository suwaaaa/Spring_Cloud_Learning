package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer //  开启注册中心服务端 http://localhost:9000/
@SpringBootApplication
//本机自测可以关闭Eureka保护配置 eureka.server.enable-self-preservation=false
//Eureka可能会声明已经不存在的实例。刷新数小于阈值时，为了安全起见不会剔除过期实例。Eureka的默认阈值为：85% Eg：（8/10=80%<85%）Eureka就会开启保护机制，过期的实例不会立马剔除
public class EurekaApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaApplication.class,args);
    }
}
