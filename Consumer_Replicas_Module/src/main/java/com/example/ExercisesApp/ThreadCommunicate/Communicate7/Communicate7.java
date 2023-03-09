package com.example.ExercisesApp.ThreadCommunicate.Communicate7;

/**
 * Java_IBM_Learning com.example.ExercisesApp.ThreadCommunicate.Communicate7
 *
 * @author 12645
 * @createTime 2023/3/9 14:27
 * @description
 *
 * 线程中的sleep()和wait()两方法的区别与联系
 * 相同点：
 * 两者都可以暂停线程的执行，都会让线程进入等待状态。
 *
 * 不同点：
 * sleep()方法没有释放锁，而 wait()方法释放了锁。
 * sleep()方法属于Thread类的静态方法，作用于当前线程；而wait()方法是Object类的实例方法，作用于对象本身。
 * 执行sleep()方法后，可以通过超时或者调用interrupt()方法唤醒休眠中的线程；执行wait()方法后，通过调用notify()或notifyAll()方法唤醒等待线程。
 */

public class Communicate7 {
    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> System.out.println(Thread.currentThread().getName() + " running 1 command"));

        Thread thread2 = new Thread(() -> System.out.println(Thread.currentThread().getName() + " running 2 command"));

        Thread thread3 = new Thread(() -> System.out.println(Thread.currentThread().getName() + " running 3 command"));

        thread2.start();
        try { thread2.join(); } catch (InterruptedException e) { e.printStackTrace();}
        thread3.start();
        try { thread3.join(); } catch (InterruptedException e) { e.printStackTrace();}
        thread1.start();

    }
}
