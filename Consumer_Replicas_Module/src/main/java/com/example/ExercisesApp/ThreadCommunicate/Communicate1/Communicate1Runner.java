package com.example.ExercisesApp.ThreadCommunicate.Communicate1;

/**
 * Java_IBM_Learning com.example.ExercisesApp.ThreadCommunicate.Communicate1
 *
 * @author 12645
 * @createTime 2023/3/8 13:30
 * @description 线程通信-- synchronized + wait + notifyAll + 条件
 */

public class Communicate1Runner {
    public static void main(String[] args) {
        ProviderConsumer1 providerConsumer = new ProviderConsumer1();

        Consumer1 consumer1 = new Consumer1(providerConsumer);
        Consumer1 consumer2 = new Consumer1(providerConsumer);

        Provider1 provider1 = new Provider1(providerConsumer);
        Provider1 provider2 = new Provider1(providerConsumer);

        new Thread(consumer1,"消费者-1").start();
        new Thread(consumer2,"消费者-2").start();
        new Thread(provider1,"生产者-1").start();
        new Thread(provider2,"生产者-2").start();
    }
}
