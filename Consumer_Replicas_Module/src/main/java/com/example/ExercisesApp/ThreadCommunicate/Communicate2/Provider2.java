package com.example.ExercisesApp.ThreadCommunicate.Communicate2;

/**
 * Java_IBM_Learning com.example.ExercisesApp.ThreadCommunicate.Communicate2Runner
 *
 * @author 12645
 * @createTime 2023/3/8 14:21
 * @description Lock + Condition + 条件
 */

public class Provider2 implements Runnable {

    private ProvoderConsumer2 provoder2;

    public Provider2(ProvoderConsumer2 provoderConsumer2) {
        this.provoder2 = provoderConsumer2;
    }

    @Override
    public void run() {
        while (true){
            provoder2.publish();
        }
    }
}
