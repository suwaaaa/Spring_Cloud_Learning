package com.JPAPrividerApp.Schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;
import java.util.Date;

/**
 * Java_IBM_Learning com.JPAPrividerApp.Schedule
 *
 * @author 12645
 * @createTime 2023/2/3 16:59
 * @description
 */
@Component
public class DynamicCronSchedule implements SchedulingConfigurer {

    private static Logger logger = LoggerFactory.getLogger(DynamicCronSchedule.class);

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        Runnable task = () -> {
            //业务执行代码
            System.out.println("Do the DynamicCronSchedule real task ...");
        };
        Trigger trigger = triggerContext -> {
            //执行于每一次任务的触发
            String cron = "*/2 * * 9 2 *";

            logger.info("cron expression is [{}]",cron);
            logger.info("trigger list size is [{}]",taskRegistrar.getTriggerTaskList().size());

            CronTrigger cronTrigger = new CronTrigger(cron);
            Date nextExecTime = cronTrigger.nextExecutionTime(triggerContext);
            return nextExecTime;
        };
        taskRegistrar.addTriggerTask(task, trigger);
    }
}
