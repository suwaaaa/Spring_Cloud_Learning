package JPAPrividerApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class JpaProvider02Application {  // Port: 9025
    public static void main(String[] args) {
        SpringApplication.run(JpaProvider02Application.class, args);
    }
}
