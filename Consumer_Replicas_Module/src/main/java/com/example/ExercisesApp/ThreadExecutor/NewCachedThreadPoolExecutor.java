package com.example.ExercisesApp.ThreadExecutor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Java_IBM_Learning com.example.ExercisesApp.ThreadExecutor
 *
 * @author 12645
 * @createTime 2023/3/8 1:31
 * @description newCachedThreadPool == 会根据需要创建新线程的线程池
 *              corePoolSize被设置为0，即corePool为空；maximumPoolSize被设置为Integer.MAX_VALUE,即maximumPool是无界的
 *              如果主线程提交任务的速度高于线程池中线程处理任务的速度的话，线程池就会不断创建新的线程
 *              极端情况下就可能导致线程创建过多而耗尽CPU和内存资源
 */

public class NewCachedThreadPoolExecutor {
    public static void main(String args[]) {
        ExecutorService pool = Executors.newCachedThreadPool();
        for (int i = 0; i <= 10; i++) {
            pool.execute(() -> {
                System.out.println(Thread.currentThread().getName() + " [running done]");
            });
        }
    }

}
