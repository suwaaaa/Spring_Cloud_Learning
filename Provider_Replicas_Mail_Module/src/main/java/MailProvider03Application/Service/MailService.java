package MailProvider03Application.Service;

import MailProvider03Application.Entity.Email;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@Service
public interface MailService {

    @PostMapping(value = "/mail/163mailSample")
    String sendMailSample(@RequestBody Map<String,Email> emailMap);

    @GetMapping(value = "/mail/helloMail")
    String helloMail();
}
