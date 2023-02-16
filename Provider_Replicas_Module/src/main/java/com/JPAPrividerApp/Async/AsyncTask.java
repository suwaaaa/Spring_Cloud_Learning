package com.JPAPrividerApp.Async;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * Java_IBM_Learning com.JPAPrividerApp.Async
 *
 * @author 12645
 * @createTime 2023/2/9 16:43
 * @description
 */
public class AsyncTask implements Runnable {  //    模拟处理1000个订单

    private CyclicBarrier cyclicBarrier;
    private CountDownLatch countDownLatch;
    public AsyncTask( CountDownLatch countDownLatch, CyclicBarrier cyclicBarrier){
        this.countDownLatch = countDownLatch;
        this.cyclicBarrier = cyclicBarrier;
    }

    private static int orderNum = 0;

    public static void setOrderNum(int orderNum) {
        AsyncTask.orderNum = orderNum;
    }

    private final Object lockAtObject = new Object();
    private void takeOrder(){
        synchronized (lockAtObject){
            if (orderNum > 0){
                orderNum --;
                System.out.println("当前线程 " + Thread.currentThread().getName() + " 处理完订单， 剩余订单:" + orderNum);
            }else { System.out.println("当前所有订单全部处理完毕!");}
        }
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {  e.printStackTrace();}
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " 线程开始准备中 ..." + " 处理总订单 " + orderNum);
        try {
            cyclicBarrier.await();    // 此处阻塞  等所有线程都到位
            while (orderNum > 0){
                takeOrder();
            }
            countDownLatch.countDown();  //当前线程结束后，计数器-1
        }catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
