package com.example.ExercisesApp.ThreadExecutor;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Java_IBM_Learning com.example.ExercisesApp.ThreadExecutor
 *
 * @author 12645
 * @createTime 2023/3/8 1:33
 * @description newScheduledThreadPool 用于实现多个线程的周期性任务
 *              会把待调度的任务放到延迟队列DelayQueue中
 *              允许创建的最大线程数也是Interger.MAX_VALUE
 *              以下逻辑实现的是：延迟10秒后开始执行任务
 */

public class NewScheduledThreadPoolExecutor {
    public static void main(String args[]) {
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(10);
        System.out.println("Ready to take task: " + new Date().toString());
        for (int i = 0; i <= 10; i++) {
            pool.schedule(() -> {
                System.out.println(Thread.currentThread().getName() + " [running done]" + new Date().toString());
            }, 10, TimeUnit.SECONDS);
        }
    }
}
