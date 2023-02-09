package Feign.Component;

import Feign.Entity.Email;
import Feign.Service.MailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

/**
 * Java_IBM_Learning Feign.Component
 *
 * @author 12645
 * @createTime 2023/2/9 14:46
 * @description
 */
@Component
public class ScheduleTask {

    private final MailSenderService mailSenderService;
    @Autowired
    public ScheduleTask(MailSenderService mailSenderService) {
        this.mailSenderService = mailSenderService;
    }

    @PostMapping(value = "/mail/163mailSample")
    @Scheduled(cron="1 16 15 9 2 *")
    public void sendMailAtSpecificTime(){
        Email email = new Email();
        email.setMailTo(new String[]{"1264584869@qq.com"});
        email.setSubject("Auto Email");
        email.setContent("Hello Test");
        Map<String,Email> map = Map.of("mail", email);
        String sentMailResult = mailSenderService.sendMailSample(map);
        System.out.println("Auto Email " + sentMailResult + " !!!");
    }
}
