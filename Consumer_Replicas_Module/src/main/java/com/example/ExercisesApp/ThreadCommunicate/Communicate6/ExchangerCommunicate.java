package com.example.ExercisesApp.ThreadCommunicate.Communicate6;

import java.util.concurrent.Exchanger;

/**
 * Java_IBM_Learning com.example.ExercisesApp.ThreadCommunicate.Communicate6
 *
 * @author 12645
 * @createTime 2023/3/8 18:11
 * @description
 */

public class ExchangerCommunicate {
    private Exchanger<String> exchanger;

    public ExchangerCommunicate(Exchanger<String> exchanger) {
        this.exchanger = exchanger;
    }

    public void personA(){
        String str = "aaa";
        System.out.println(Thread.currentThread().getName() + " :开始挖掘数据");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) { e.printStackTrace(); }
        System.out.println(Thread.currentThread().getName() + " :挖掘数据完毕，数据为：" + str);
        try {
            String ss = exchanger.exchange(str);
            System.out.println(Thread.currentThread().getName() + " :交换后得到数据，数据为："+ ss);
        } catch (InterruptedException e) { e.printStackTrace(); }
    }

    public void personB() {
        String str = "bbb";
        System.out.println(Thread.currentThread().getName() + " :开始挖掘数据");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) { e.printStackTrace();}
        System.out.println(Thread.currentThread().getName() + " :挖掘数据完毕，数据为："+ str);
        try {String ss = exchanger.exchange(str);
            System.out.println(Thread.currentThread().getName() + " :交换后得到数据，数据为："+ ss);
        } catch (InterruptedException e) { e.printStackTrace();}
    }

}
