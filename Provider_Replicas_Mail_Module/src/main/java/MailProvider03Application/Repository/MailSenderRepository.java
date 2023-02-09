package MailProvider03Application.Repository;

import MailProvider03Application.Entity.Email;
import MailProvider03Application.Service.MailService;
import org.bouncycastle.mime.Headers;
import org.bouncycastle.mime.MimeContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * Java_IBM_Learning MailProvider03Application.Repository
 *
 * @author 12645
 * @createTime 2023/2/3 18:35
 * @description
 */
@Component
public class MailSenderRepository implements MailService {

    @Value("${spring.mail.username}")
    private String from;

    private org.springframework.mail.MailSender mailSender;

    public MailSenderRepository(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    @PostMapping(value = "/mail/163mailSample")
    public String sendMailSample(@RequestBody Map<String,Email> emailMap) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(emailMap.get("mail").getMailTo());
        message.setSubject(emailMap.get("mail").getSubject());
        message.setText(emailMap.get("mail").getContent());
        System.out.println("1.Mail Provider Server --" + emailMap);
        try {
            mailSender.send(message);
            return "发送普通邮件成功";
        } catch (MailException e) {
            e.printStackTrace();
            return "普通邮件方失败";
        }
    }

    @Override
    @GetMapping(value = "/mail/helloMail")
    public String helloMail() {
        return "This is Mail Provider, hello !";
    }
}