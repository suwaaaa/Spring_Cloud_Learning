package com.example.ExercisesApp.ThreadCommunicate.Communicate6;

/**
 * Java_IBM_Learning com.example.ExercisesApp.ThreadCommunicate.Communicate6
 *
 * @author 12645
 * @createTime 2023/3/8 18:14
 * @description
 */

public class ExchangerA implements Runnable{

    private ExchangerCommunicate exchangerA;

    public ExchangerA(ExchangerCommunicate exchangerA) {
        this.exchangerA = exchangerA;
    }

    @Override
    public void run() {
        exchangerA.personA();
    }
}
