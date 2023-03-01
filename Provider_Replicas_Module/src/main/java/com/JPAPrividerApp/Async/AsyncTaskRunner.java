package com.JPAPrividerApp.Async;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.concurrent.*;

/**
 * Java_IBM_Learning com.JPAPrividerApp.Async
 *
 * @author 12645
 * @createTime 2023/2/9 16:43
 * @description
 */
@Data
@Accessors(chain = true)
public class AsyncTaskRunner implements Runnable {  //    模拟处理1000个订单

    private Integer threadNum = 1;    //模拟多个线程
    private CyclicBarrier cyclicBarrier;
    private CountDownLatch countDownLatch;  // 用于统计 执行时长
    private AsyncTaskRunner threadPool;
    private ExecutorService executorService;
    private Integer orderNum = 0;

    public AsyncTaskRunner(CyclicBarrier cyclicBarrier, CountDownLatch countDownLatch) {
        this.cyclicBarrier = cyclicBarrier;
        this.countDownLatch = countDownLatch;
    }
    public void setExecutorService(ExecutorService executorService) {
        this.executorService = executorService;
    }

    private final Object lockAtObject = new Object();
    private void takeOrder(){
        synchronized (lockAtObject){
            if (orderNum > 0){
                orderNum--;
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
