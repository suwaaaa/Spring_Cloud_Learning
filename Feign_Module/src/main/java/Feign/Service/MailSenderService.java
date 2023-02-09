package Feign.Service;

import Feign.Entity.Email;
import Feign.FallBackHandler.MyFallBackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

/**
 * Java_IBM_Learning com.JPAPrividerApp.Service
 *
 * @author 12645
 * @createTime 2023/2/3 21:07
 * @description
 */

@FeignClient(name = "Spring-Cloud-Eureka-Provider-03-Mail", fallbackFactory = MyFallBackFactory.class)
@Component
public interface MailSenderService {

    @PostMapping(value = "/mail/163mailSample")
    String sendMailSample(@RequestBody Map<String,Email> emailMap);

    @GetMapping(value = "/mail/helloMail")
    String helloMail();
}
