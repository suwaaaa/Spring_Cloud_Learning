package Feign.Controller;

import Feign.Component.ScheduleTask;
import Feign.Entity.Email;
import Feign.Service.MailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Java_IBM_Learning com.JPAPrividerApp.Controller
 *
 * @author 12645
 * @createTime 2023/2/3 19:17
 * @description
 */
@RestController
public class MailSenderController {
    private final MailSenderService mailSenderService;
    private final ScheduleTask scheduleTask;
    @Autowired
    public MailSenderController(MailSenderService mailSenderService, ScheduleTask scheduleTask) {
        this.mailSenderService = mailSenderService;
        this.scheduleTask = scheduleTask;
    }

    @PostMapping(value = "/mail/163mailSample")
    @ResponseBody
    public String sendMailSample(@RequestBody Map<String,Email> emailMap){
        System.out.println("1.Feign Server --" + emailMap);
        return mailSenderService.sendMailSample(emailMap);
    }

    /*@PostMapping(value = "/mail/163mailSample")
    @ResponseBody
    public void sendMailAtSpecificTime(){
       scheduleTask.sendMailAtSpecificTime();
    }*/

    @GetMapping(value = "/mail/helloMail")
    public String helloMail(){
        return mailSenderService.helloMail();
    }
}
