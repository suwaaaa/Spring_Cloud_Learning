package com.example.ExercisesApp.ThreadRunner;

import com.example.ExercisesApp.Threads.RunnableThread.ExtendsThread;

/**
 * Java_IBM_Learning com.example.ExercisesApp.Controller
 *
 * @author 12645
 * @createTime 2023/3/7 14:23
 * @description
 */

public class ExtendsThreadRunner {
    public static void main(String[] args) {
        ExtendsThread extendsThread1 = new ExtendsThread();
        ExtendsThread extendsThread2 = new ExtendsThread();
        ExtendsThread extendsThread3 = new ExtendsThread();
        ExtendsThread extendsThread4 = new ExtendsThread();

        ExtendsThread.setCount(10000);

        extendsThread1.setName("Thread-1");
        extendsThread2.setName("Thread-2");
        extendsThread3.setName("Thread-3");
        extendsThread4.setName("Thread-4");

        extendsThread1.start();
        extendsThread2.start();
        extendsThread3.start();
        extendsThread4.start();
    }
}
