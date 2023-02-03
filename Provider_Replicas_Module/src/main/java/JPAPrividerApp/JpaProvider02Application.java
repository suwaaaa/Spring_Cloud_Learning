package JPAPrividerApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
//去除spring security 的权限校验
@SpringBootApplication//(exclude = {SecurityAutoConfiguration.class, ManagementWebSecurityAutoConfiguration.class})
@EnableEurekaClient
public class JpaProvider02Application {  // Port: 9025
    public static void main(String[] args) {
        SpringApplication.run(JpaProvider02Application.class, args);
    }
}
