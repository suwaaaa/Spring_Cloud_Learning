package com.example.ExercisesApp.ThreadExecutor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Java_IBM_Learning com.example.ExercisesApp.ThreadExecutor
 *
 * @author 12645
 * @createTime 2023/3/8 1:12
 * @description newSingleThreadExecutor 是单个工作线程的Executor
 *              corePoolSize和maximumPoolSize被设置为1
 *              采用的是无界队列LinkedBlockingQueue作为线程池的工作队列（队列的容量为Interger.MAX_VALUE）
 *              如果请求过多会导致OOM，在并发请求量比较大的系统中，使用此线程池需要注意
 */
public class SingleThreadExecutor {
    public static void main(String[] args) {
        ExecutorService executorServicePool = Executors.newSingleThreadExecutor();
        for (int i = 0; i <= 5; i++) {
            executorServicePool.execute(() -> System.out.println(Thread.currentThread().getName() + "  [running done]"));
        }

    }
}
