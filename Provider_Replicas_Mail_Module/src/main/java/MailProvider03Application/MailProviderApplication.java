package MailProvider03Application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Java_IBM_Learning MailProvider03Application
 *
 * @author 12645
 * @createTime 2023/2/3 18:02
 * @description
 */
@SpringBootApplication
@EnableEurekaClient
public class MailProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run( MailProviderApplication.class, args);
    }
}
