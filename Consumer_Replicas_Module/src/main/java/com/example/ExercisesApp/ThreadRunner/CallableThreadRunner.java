package com.example.ExercisesApp.ThreadRunner;

import com.example.ExercisesApp.Threads.RunnableThread.CallableThread;

import java.util.concurrent.FutureTask;

/**
 * Java_IBM_Learning com.example.ExercisesApp.ThreadRunner
 *
 * @author 12645
 * @createTime 2023/3/7 15:24
 * @description
 */

public class CallableThreadRunner {
    public static void main(String[] args) {
        CallableThread.setCount(500);
        FutureTask<Integer> futureTask1 = new FutureTask<>(new CallableThread());
        FutureTask<Integer> futureTask2 = new FutureTask<>(new CallableThread());
        FutureTask<Integer> futureTask3 = new FutureTask<>(new CallableThread());
        FutureTask<Integer> futureTask4 = new FutureTask<>(new CallableThread());

        Thread thread1 = new Thread(futureTask1);
        Thread thread2 = new Thread(futureTask2);
        Thread thread3 = new Thread(futureTask3);
        Thread thread4 = new Thread(futureTask4);

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }
}
