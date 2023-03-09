package com.example.ExercisesApp.ThreadExecutor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Java_IBM_Learning com.example.ExercisesApp.ThreadExecutor
 *
 * @author 12645
 * @createTime 2023/3/8 1:29
 * @description newFixedThreadPool == 可重用固定线程数线程池
 *              使用了无界队列作为工作队列，如果没有执行方法shutdown()的话也是不会拒绝任务的
 */

public class NewFixedThreadPoolExecutor {
    public static void main(String args[]) {
        ExecutorService pool = Executors.newFixedThreadPool(10);
        for (int i = 0; i <= 15; i++) {
            pool.execute(() -> {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ignored) {
                }
                System.out.println(Thread.currentThread().getName() + " [running done]");
            });
        }
    }

}
