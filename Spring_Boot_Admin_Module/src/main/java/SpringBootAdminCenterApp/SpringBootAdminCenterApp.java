package SpringBootAdminCenterApp;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Java_IBM_Learning PACKAGE_NAME
 *
 * @author 12645
 * @createTime 2023/2/4 3:45
 * @description
 */
@SpringBootApplication
@EnableAdminServer
@EnableEurekaClient
public class SpringBootAdminCenterApp {

    public static void main(String[] args) {
        SpringApplication.run( SpringBootAdminCenterApp.class, args);
    }
}
