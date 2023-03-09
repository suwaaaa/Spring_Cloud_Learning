package com.example.ExercisesApp.ThreadCommunicate.Communicate3;

/**
 * Java_IBM_Learning com.example.ExercisesApp.ThreadCommunicate.Communicate3
 *
 * @author 12645
 * @createTime 2023/3/8 16:21
 * @description
 */

public class Communicate3 implements Runnable {
    private SemaphoreCommunicate semaphoreCommunicate;

    public Communicate3(SemaphoreCommunicate semaphoreCommunicateProvider) {
        this.semaphoreCommunicate = semaphoreCommunicateProvider;
    }

    @Override
    public void run() {
        semaphoreCommunicate.consume();
    }
}
