package com.example.ExercisesApp.ThreadRunner;

import com.example.ExercisesApp.Threads.RunnableThread.RunnableThread;

/**
 * Java_IBM_Learning com.example.ExercisesApp.Controller
 *
 * @author 12645
 * @createTime 2023/3/7 14:54
 * @description
 */

public class RunnableThreadRunner {
    public static void main(String[] args) {
        RunnableThread runnableThread = new RunnableThread();
        runnableThread.setCount(5000);
        new Thread(runnableThread,"Thread-1").start();
        new Thread(runnableThread,"Thread-2").start();
        new Thread(runnableThread,"Thread-3").start();
        new Thread(runnableThread,"Thread-4").start();
    }
}
