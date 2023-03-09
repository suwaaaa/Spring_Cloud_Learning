package com.example.ExercisesApp.ThreadCommunicate.Communicate3;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * Java_IBM_Learning com.example.ExercisesApp.ThreadCommunicate.Communicate3
 *
 * @author 12645
 * @createTime 2023/3/8 15:47
 * @description
 */

public class SemaphoreCommunicate {
    private Semaphore semaphore = new Semaphore(5);
    private volatile int count;
    private final int MAX_VALUE = 10;
    private final int MIN_VALUE = 0;

    public synchronized void add(){
//        while (count <= MIN_VALUE) try {
//            this.wait();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        System.out.println(Thread.currentThread().getName() + " :订单增加 " + ++count);
//        this.notifyAll();
    }
    public synchronized void discount(){
//        while (count > MAX_VALUE) try {
//            this.wait();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        System.out.println(Thread.currentThread().getName() + " :订单消费 " + --count);
//        this.notifyAll();
    }

    public void consume(){
        try {
            semaphore.acquire();
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        //do something
        if (count>new Random().nextInt(10)+2) {
            discount();
        }else {
            add();
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
        }
        System.out.println(Thread.currentThread().getName() + " :订单整合-完毕 ");
        semaphore.release();
    }
}
