package com.example.ExercisesApp.ThreadCommunicate.Communicate3;

/**
 * Java_IBM_Learning com.example.ExercisesApp.ThreadCommunicate.Communicate3
 *
 * @author 12645
 * @createTime 2023/3/8 16:23
 * @description 线程需要通过acquire()方法获取许可，而release()释放许可
 *              如果许可数达到最大活动数，那么调用acquire()之后，便进入等待队列等待
 *              已获得许可的线程释放许可，从而使得多线程能够合理的运行。
 */

public class CommunicateRunner3 {
    public static void main(String[] args) {
        SemaphoreCommunicate semaphoreCommunicate = new SemaphoreCommunicate();
        Communicate3 communicate3 = new Communicate3(semaphoreCommunicate);

        for (int i = 0; i < 50; i++) {
            new Thread(communicate3).start();
        }


    }
}
