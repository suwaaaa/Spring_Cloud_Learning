package com.example.ExercisesApp.ThreadCommunicate.Communicate2;

/**
 * Java_IBM_Learning com.example.ExercisesApp.ThreadCommunicate.Communicate2Runner
 *
 * @author 12645
 * @createTime 2023/3/8 14:23
 * @description Lock + Condition + 条件
 *              ReentrantLock 在Java也是一个基础的锁，ReentrantLock 实现Lock接口提供一系列的基础函数(比如说lock()和unlock()方法)
 *
 *              ReentrantLock主要利用CAS+AQS队列来实现。它支持公平锁和非公平锁，两者的实现类似
 *
 *              CAS：Compare and Swap，比较并交换。在Java中，CAS主要是由sun.misc.Unsafe这个类通过JNI调用CPU底层指令实现
 *              AQS：AbstractQueuedSynchronizer的简称。是一个用于构建锁和同步容器的框架。事实上concurrent包内许多类都是基于AQS构建，
 *              例如ReentrantLock，Semaphore，CountDownLatch，ReentrantReadWriteLock，FutureTask等。AQS解决了在实现同步容器时设计的大量细节问题
 *
 *              ReentrantLock.Condition是在粒度和性能上都优于Object的notify()、wait()、notifyAll()线程通信的方式
 *              Condition中通信方法相对Object的通信在粒度上是粒度更细化，表现在一个Lock对象上引入多个Condition监视器、通信方法中除了和Object对应的三个基本函数外，更是新增了线程中断、阻塞超时的函数
 *              Condition中通信方法相对Object的通信在性能上更高效，性能的优化表现在ReentrantLock比较synchronized的优化
 *
 *              注意：在使用ReentrantLock.Condition中使用signal()、await()、signalAll()方法，不能和Object的notify()、wait()、notifyAll()方法混用，否则抛出异常
 */

public class Communicate2Runner {
    public static void main(String[] args) {
        ProvoderConsumer2 provoderConsumer2 = new ProvoderConsumer2();

        Provider2 provider1 = new Provider2(provoderConsumer2);
        Provider2 provider2 = new Provider2(provoderConsumer2);

        Consumer2 consumer1 = new Consumer2(provoderConsumer2);
        Consumer2 consumer2 = new Consumer2(provoderConsumer2);

        new Thread(provider1,"生产者-1").start();
        new Thread(provider2,"生产者-2").start();
        new Thread(consumer1,"消费者-1").start();
//        new Thread(consumer2,"消费者-2").start();

    }
}
