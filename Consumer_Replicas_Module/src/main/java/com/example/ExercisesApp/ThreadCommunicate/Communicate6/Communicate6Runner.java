package com.example.ExercisesApp.ThreadCommunicate.Communicate6;

import java.util.concurrent.Exchanger;

/**
 * Java_IBM_Learning com.example.ExercisesApp.ThreadCommunicate.Communicate6
 *
 * @author 12645
 * @createTime 2023/3/8 18:16
 * @description java.util.concurrent包中的Exchanger类可用于两个线程之间交换信息
 *              可简单地将Exchanger对象理解为一个包含两个格子的容器，通过exchanger方法可以向两个格子中填充信息。
 *              当两个格子中的均被填充时，该对象会自动将两个格子的信息交换，
 *              然后返回给线程，从而实现两个线程的信息交换。
 *
 *              Exchanger类的核心便是exchange()方法的使用，exchange()方法的线程会进行交换数据
 */

public class Communicate6Runner {
    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<>();
        ExchangerCommunicate exchangerCommunicate = new ExchangerCommunicate(exchanger);

        new Thread(new ExchangerA(exchangerCommunicate)).start();
        new Thread(new ExchangerB(exchangerCommunicate)).start();
    }
}
