package com.example.ExercisesApp.Threads.Simple;

/**
 * Java_IBM_Learning com.example.ExercisesApp.Threads
 *
 * @author 12645
 * @createTime 2023/3/7 12:33
 * @description 创建2个线程对象
 */

public class MultipleThread extends Thread {
//    private int count = 100;//错误写法，应该写在静态区，如下
    private static int count = 100;
    public void run(){
        while (true){
            if (count>0) {
                System.out.println(Thread.currentThread().getName() + " is dealing with " + count--);
            }else {
                break;
            }
        }
    }
}
