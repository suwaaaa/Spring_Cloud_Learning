package Publish;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Java_IBM_Learning Publish
 *
 * @author 12645
 * @createTime 2023/3/14 17:03
 * @description  Rabbit MQ link: http://192.168.3.128:15672/#/exchanges (Should oprn Vmware 15 Pro and running CentOS 7 Docker)
 */

@SpringBootApplication
public class PublishProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run( PublishProviderApplication.class, args);
    }
}
