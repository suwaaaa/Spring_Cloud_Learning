package com.example.ExercisesApp.ThreadCommunicate.Communicate4;

/**
 * Java_IBM_Learning com.example.ExercisesApp.ThreadCommunicate.Communicate4
 *
 * @author 12645
 * @createTime 2023/3/8 17:16
 * @description
 */

public class Communicate4 implements Runnable {

    private CountDownLatchCommunicate countDownLatchCommunicate;

    public Communicate4(CountDownLatchCommunicate countDownLatchCommunicate) {
        this.countDownLatchCommunicate = countDownLatchCommunicate;
    }

    @Override
    public void run() {
        countDownLatchCommunicate.messageReceive();
    }
}
