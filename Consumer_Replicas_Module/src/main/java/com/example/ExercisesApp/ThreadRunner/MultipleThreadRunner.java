package com.example.ExercisesApp.ThreadRunner;

import com.example.ExercisesApp.Threads.Simple.MultipleThread;

/**
 * Java_IBM_Learning com.example.ExercisesApp.Controller
 *
 * @author 12645
 * @createTime 2023/3/7 12:37
 * @description
 */

public class MultipleThreadRunner {
    public static void main(String[] args) {
        MultipleThread multipleThread1 = new MultipleThread();
        MultipleThread multipleThread2 = new MultipleThread();

        multipleThread1.setName("Thread-1");
        multipleThread2.setName("Thread-2");

        multipleThread1.start();//不是run方法
        multipleThread2.start();
    }
}
