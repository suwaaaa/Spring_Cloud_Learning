package com.example.ExercisesApp.Threads.RunnableThread;

/**
 * Java_IBM_Learning com.example.ExercisesApp.Threads.RunnableThread
 *
 * @author 12645
 * @createTime 2023/3/7 14:15
 * @description
 */

public class ExtendsThread extends Thread {
    private static final Object lockObject = new Object();
    private static int count;//static !!!

    public static void setCount(int count) {
        ExtendsThread.count = count;
    }

    public void run(){
        Thread thread = Thread.currentThread();
        while (true){
            synchronized (lockObject){
                if (count>0) {
                    System.out.println(thread.getName() + " taken count: " + count--);
                }else {
                    break;
                }
            }
        }
    }
}
