package com.example.ExercisesApp.Threads.RunnableThread;

/**
 * Java_IBM_Learning com.example.ExercisesApp.Threads.RunnableThread
 *
 * @author 12645
 * @createTime 2023/3/7 14:45
 * @description
 */

public class RunnableThread implements Runnable{
    private int count;

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public void run() {
        while (true){
            synchronized (this){
                if (count>0) {
                    System.out.println(Thread.currentThread().getName() + " doing count >> " + count--);
                }else {
                    break;
                }
            }
        }
    }
}
