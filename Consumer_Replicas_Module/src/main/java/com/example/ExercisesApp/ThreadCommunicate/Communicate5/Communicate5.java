package com.example.ExercisesApp.ThreadCommunicate.Communicate5;

/**
 * Java_IBM_Learning com.example.ExercisesApp.ThreadCommunicate.Communicate5
 *
 * @author 12645
 * @createTime 2023/3/8 17:50
 * @description
 */

public class Communicate5 implements Runnable{
    private CyclicbarrierCommunicate cyclicbarrierCommunicate;

    public Communicate5(CyclicbarrierCommunicate cyclicbarrierCommunicate) {
        this.cyclicbarrierCommunicate = cyclicbarrierCommunicate;
    }

    @Override
    public void run() {
        cyclicbarrierCommunicate.packingQueue();
    }
}
