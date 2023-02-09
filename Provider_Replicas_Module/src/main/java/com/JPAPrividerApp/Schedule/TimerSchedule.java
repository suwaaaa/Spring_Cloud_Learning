package com.JPAPrividerApp.Schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Java_IBM_Learning com.JPAPrividerApp.Schedule
 *
 * @author 12645
 * @createTime 2023/2/3 11:58
 * @description 定时任务实现
 */
@Component
public class TimerSchedule {

    private static final Logger log = LoggerFactory.getLogger(TimerSchedule.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    /*
     * fixedRate: 定时多久执行一次                              [固定频率]
     * fixedDelay: 上一次执行结束时间点后xx秒再次执行             [固定间隙]
     * fixedDelayString: 字符串形式，可以通过配置文件指定
     */
    @Scheduled(fixedRate = 2000)   //每两秒打印一次  (静态cron定时调度)
    public void printTaskByFixedRate() {
        log.info("The printTaskByFixedRate is now {}", dateFormat.format(new Date()));
    }

    @Scheduled(cron="*/10 * * * * *")    //	cron 定时任务表达式,表示每秒  (静态cron定时调度)
    public void printTaskByCron() {
        log.info("The printTaskByCron is now {}", dateFormat.format(new Date()));
    }

}
