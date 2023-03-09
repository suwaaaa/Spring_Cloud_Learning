package com.example.ExercisesApp.ThreadCommunicate.Communicate5;

import java.util.concurrent.CyclicBarrier;

/**
 * Java_IBM_Learning com.example.ExercisesApp.ThreadCommunicate.Communicate5
 *
 * @author 12645
 * @createTime 2023/3/8 17:51
 * @description Cyclicbarrier是JDK1.5的java.util.concurrent并发包中提供的一个并发工具类。
 *              CyclicBarrier是一个同步辅助类
 *              它允许一组线程相互等待直到所有线程都到达一个公共的屏障点,在程序中有固定数量的线程，这些线程有时候必须等待彼此
 *              CyclicBarrier可以重复计数，计数器可重复使用。
 */

public class Communicate5Runner {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(10, new Runnable() {
            @Override
            public void run() {
                System.out.println(" Packing queue enough! All set up finish ... !");
            }
        });
        Communicate5 communicate5 = new Communicate5(new CyclicbarrierCommunicate(cyclicBarrier));
        for (int i = 0; i < 20; i++) {
            new Thread(communicate5).start();
        }
    }
}
