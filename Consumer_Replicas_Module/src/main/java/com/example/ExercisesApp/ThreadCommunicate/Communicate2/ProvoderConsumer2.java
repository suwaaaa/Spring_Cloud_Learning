package com.example.ExercisesApp.ThreadCommunicate.Communicate2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Java_IBM_Learning com.example.ExercisesApp.ThreadCommunicate.Communicate2Runner
 *
 * @author 12645
 * @createTime 2023/3/8 14:09
 * @description  Lock + Condition + 条件
 */

public class ProvoderConsumer2 {
    private volatile int count;//共享资源
    private final int MAX_VALUE = 10;
    private final int MIN_VALUE = 0;
    private Lock lock = new ReentrantLock();//可重入锁

    private Condition provideCondition = lock.newCondition();
    private Condition consumeCondition = lock.newCondition();

    public void publish(){
        lock.lock();
        while (count >= MAX_VALUE) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+ " :停止生产，当前库存：" + count);
            try {
                consumeCondition.await(); //生产者等待
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        count++;//volatile变量
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " :正在生产，库存：" + count);
        provideCondition.signalAll();//生产者唤醒
        lock.unlock();//生产者解锁
    }

    public void consume(){
        lock.lock();
        while (count <= MIN_VALUE) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+ " :停止消费，当前库存：" + count);
            try {
                provideCondition.await();//消费等待
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        count--;//volatile变量
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " :正在消费，库存："+ count);
        consumeCondition.signalAll();//消费唤醒
        lock.unlock();
    }
}
