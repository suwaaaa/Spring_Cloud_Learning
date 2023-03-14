package Consume;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Java_IBM_Learning Consume
 *
 * @author 12645
 * @createTime 2023/3/14 17:50
 * @description  Rabbit MQ link: http://192.168.3.128:15672/#/exchanges (Should oprn Vmware 15 Pro and running CentOS 7 Docker)
 */
@SpringBootApplication
public class ConsumeMessageApplication {
    public static void main(String[] args) {
        SpringApplication.run( ConsumeMessageApplication.class, args);
    }
}
