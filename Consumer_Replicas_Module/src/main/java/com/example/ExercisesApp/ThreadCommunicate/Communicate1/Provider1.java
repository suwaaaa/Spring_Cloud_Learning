package com.example.ExercisesApp.ThreadCommunicate.Communicate1;

/**
 * Java_IBM_Learning com.example.ExercisesApp.ThreadCommunicate.Communicate
 *
 * @author 12645
 * @createTime 2023/3/8 13:25
 * @description 线程通信-- synchronized + wait + notifyAll + 条件
 */

public class Provider1 implements Runnable {

    private ProviderConsumer1 provider1;

    public Provider1(ProviderConsumer1 providerConsumer1) {
        this.provider1 = providerConsumer1;
    }

    @Override
    public void run() {
        while (true){
            provider1.provider();
        }
    }
}
