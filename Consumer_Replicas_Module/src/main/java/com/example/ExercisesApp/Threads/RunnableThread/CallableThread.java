package com.example.ExercisesApp.Threads.RunnableThread;

import java.util.concurrent.Callable;

/**
 * Java_IBM_Learning com.example.ExercisesApp.Threads.RunnableThread
 *
 * @author 12645
 * @createTime 2023/3/7 14:59
 * @description 实现Callable接口是有返回结果的
 */

public class CallableThread implements Callable<Integer> {
    private static int count;

    public static void setCount(int count) {
        CallableThread.count = count;
    }

    @Override
    public Integer call() throws Exception {
        if (count>0) {
            takingCount();
        }
        return count;
    }

    private synchronized void takingCount(){//synchronized !!!
        while (count>0){
            System.out.println(Thread.currentThread().getName() + " takingCount >> " + count--);
        }
    }
}
