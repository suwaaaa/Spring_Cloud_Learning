package com.example.ExercisesApp.ThreadCommunicate.Communicate6;

/**
 * Java_IBM_Learning com.example.ExercisesApp.ThreadCommunicate.Communicate6
 *
 * @author 12645
 * @createTime 2023/3/8 18:15
 * @description
 */

public class ExchangerB implements  Runnable{
    private ExchangerCommunicate exchangerB;

    public ExchangerB(ExchangerCommunicate exchangerB) {
        this.exchangerB = exchangerB;
    }

    @Override
    public void run() {
        exchangerB.personB();
    }
}
