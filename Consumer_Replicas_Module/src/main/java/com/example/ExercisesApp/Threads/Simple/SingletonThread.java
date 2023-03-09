package com.example.ExercisesApp.Threads.Simple;

/**
 * Java_IBM_Learning com.example.ExercisesApp.Threads
 *
 * @author 12645
 * @createTime 2023/3/7 12:19
 * @description
 */

public class SingletonThread extends Thread{

    private Thread thread = currentThread();
    public void run(){
        thread.setName("Thread-1");
        int lockForOrder = 100;
        for (int i = 0; i < lockForOrder; i++) {
            System.out.println(thread.getName() + " is counting " + (i+1));
        }
    }
}
