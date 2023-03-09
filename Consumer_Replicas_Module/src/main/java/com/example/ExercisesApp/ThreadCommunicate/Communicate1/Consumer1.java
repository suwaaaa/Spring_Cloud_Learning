package com.example.ExercisesApp.ThreadCommunicate.Communicate1;

/**
 * Java_IBM_Learning com.example.ExercisesApp.ThreadCommunicate.Communicate
 *
 * @author 12645
 * @createTime 2023/3/8 13:28
 * @description 线程通信-- synchronized + wait + notifyAll + 条件
 */

public class Consumer1 implements Runnable{

    private ProviderConsumer1 consumer1;

    public Consumer1(ProviderConsumer1 consumer1) {
        this.consumer1 = consumer1;
    }

    @Override
    public void run() {
        while (true){
            consumer1.consumer();
        }
    }
}
