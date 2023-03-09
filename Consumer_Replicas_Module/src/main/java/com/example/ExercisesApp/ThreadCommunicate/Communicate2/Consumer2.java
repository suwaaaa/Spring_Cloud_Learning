package com.example.ExercisesApp.ThreadCommunicate.Communicate2;

import javax.swing.plaf.RootPaneUI;

/**
 * Java_IBM_Learning com.example.ExercisesApp.ThreadCommunicate.Communicate2Runner
 *
 * @author 12645
 * @createTime 2023/3/8 14:22
 * @description Lock + Condition + 条件
 */

public class Consumer2 implements Runnable {
    private ProvoderConsumer2 consumer2;

    public Consumer2(ProvoderConsumer2 consumer2) {
        this.consumer2 = consumer2;
    }

    @Override
    public void run() {
        while (true){
            consumer2.consume();
        }
    }
}
