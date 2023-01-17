package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableEurekaClient          //  启动Eureka客户端支持
@EnableDiscoveryClient       //@EnableDiscoveryClient注解用来将当前应用加入到服务治理体系中。
@EnableCircuitBreaker        //  开启熔断器功能
@SpringBootApplication
@EnableFeignClients
public class ConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run( ConsumerApplication.class, args);
    }
}
