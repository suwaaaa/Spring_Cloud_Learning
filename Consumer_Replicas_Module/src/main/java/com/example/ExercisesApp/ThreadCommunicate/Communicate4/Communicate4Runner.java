package com.example.ExercisesApp.ThreadCommunicate.Communicate4;

import java.util.concurrent.CountDownLatch;

/**
 * Java_IBM_Learning com.example.ExercisesApp.ThreadCommunicate.Communicate4
 *
 * @author 12645
 * @createTime 2023/3/8 17:17
 * @description CountDownLatch： 一个可以用来协调多个线程之间的同步，或者说起到线程之间的通信作用的工具类
 *              它能够使一个线程在等待另外一些线程完成各自工作之后，再继续执行
 *              使用一个计数器进行实现。计数器初始值为线程的数量。当每一个线程完成自己任务后，计数器的值就会减一。
 *              当计数器的值为0时，表示所有的线程都已经完成了任务，然后CountDownLatch上等待的线程就可以恢复执行任务
 *
 *              CountDownLatch原理：CountDownLatch是通过一个计数器来实现的，计数器的初始化值为线程的数量。
 *              每当一个线程完成了自己的任务后，计数器的值就相应得减1。当计数器到达0时，表示所有的线程都已完成任务，然后在闭锁上等待的线程就可以恢复执行任务。
 */

public class Communicate4Runner {
    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(20);
        Communicate4 communicate4 = new Communicate4(new CountDownLatchCommunicate(countDownLatch));
        for (int i = 0; i < countDownLatch.getCount(); i++) {//for 循环为多线程执行，执行效率 异步大于同步
            new Thread(communicate4).start();
        }
        try {
            countDownLatch.await();//阻塞等待
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("All finish ...");
    }
}
