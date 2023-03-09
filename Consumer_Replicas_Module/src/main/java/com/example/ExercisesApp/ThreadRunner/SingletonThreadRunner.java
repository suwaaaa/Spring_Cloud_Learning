package com.example.ExercisesApp.ThreadRunner;

import com.example.ExercisesApp.Threads.Simple.SingletonThread;

/**
 * Java_IBM_Learning com.example.ExercisesApp.Controller
 *
 * @author 12645
 * @createTime 2023/3/7 12:25
 * @description
 */

public class SingletonThreadRunner {
    public static void main(String[] args) {
        SingletonThread singletonThread = new SingletonThread();
        singletonThread.run();
    }


}
