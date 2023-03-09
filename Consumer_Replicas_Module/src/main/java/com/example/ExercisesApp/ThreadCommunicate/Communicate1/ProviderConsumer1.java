package com.example.ExercisesApp.ThreadCommunicate.Communicate1;

/**
 * Java_IBM_Learning com.example.ExercisesApp.ThreadCommunicate
 *
 * @author 12645
 * @createTime 2023/3/8 13:09
 * @description 线程通信-- synchronized + wait + notifyAll + 条件
 */

public class ProviderConsumer1 {
    private volatile int count;
    private final int MAX_VALUE = 10;
    private final int MIN_VALUE = 0;

    public synchronized void provider(){
        while (count >= MAX_VALUE) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+ " :停止生产，当前库存：" + count);
            try {
                this.wait(); //生产等待
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
        this.notifyAll();//生产唤醒
    }

    public synchronized void consumer(){
        while (count <= MIN_VALUE) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+ " :停止消费，当前库存：" + count);
            try {
                this.wait();//消费等待
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
        this.notifyAll();//消费唤醒
    }
}
