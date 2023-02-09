package MailProvider03Application.Controller;

import MailProvider03Application.Entity.Email;
import MailProvider03Application.Repository.MailSenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Java_IBM_Learning MailProvider03Application.Controller
 *
 * @author 12645
 * @createTime 2023/2/3 18:48
 * @description
 */
@RestController
public class MailSenderController {
    private final MailSenderRepository mailSenderRepository;
    @Autowired
    public MailSenderController(MailSenderRepository mailSenderRepository) {
        this.mailSenderRepository = mailSenderRepository;
    }

    @PostMapping(value = "/mail/163mailSample")
    public String sendMailSample(@RequestBody Map<String,Email> emailMap){
        return mailSenderRepository.sendMailSample(emailMap);
    }

    @GetMapping(value = "/mail/helloMail")
    public String helloMail() {
        return mailSenderRepository.helloMail();
    }
}
