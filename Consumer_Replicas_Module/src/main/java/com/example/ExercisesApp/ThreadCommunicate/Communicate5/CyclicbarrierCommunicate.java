package com.example.ExercisesApp.ThreadCommunicate.Communicate5;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Java_IBM_Learning com.example.ExercisesApp.ThreadCommunicate.Communicate5
 *
 * @author 12645
 * @createTime 2023/3/8 17:45
 * @description
 */

public class CyclicbarrierCommunicate {
    private CyclicBarrier cyclicBarrier;

    public CyclicbarrierCommunicate(CyclicBarrier cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
    }

    void packingQueue(){
        System.out.println(Thread.currentThread().getName() + " Ready to waiting for entering ...");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " Right now is in the Queue !");
        try {
            cyclicBarrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
