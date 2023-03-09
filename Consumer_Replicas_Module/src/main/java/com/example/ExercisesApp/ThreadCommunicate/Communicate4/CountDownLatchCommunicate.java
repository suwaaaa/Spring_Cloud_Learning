package com.example.ExercisesApp.ThreadCommunicate.Communicate4;

import java.util.concurrent.CountDownLatch;

/**
 * Java_IBM_Learning com.example.ExercisesApp.ThreadCommunicate.Communicate4
 *
 * @author 12645
 * @createTime 2023/3/8 17:12
 * @description
 */

public class CountDownLatchCommunicate {

    private CountDownLatch countDownLatch;

    public CountDownLatchCommunicate(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    public void messageReceive(){
        System.out.println(Thread.currentThread().getName() + " You has received mail !");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ignored) {}
        countDownLatch.countDown();
    }
}
