package com.JPAPrividerApp.Controller;

import com.JPAPrividerApp.Async.AsyncTask;
import com.JPAPrividerApp.Entity.OrderAsyncTask;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Executors;

/**
 * Java_IBM_Learning com.JPAPrividerApp.Controller
 *
 * @author 12645
 * @createTime 2023/2/9 18:09
 * @description
 */
@Controller
public class OrderController {

    @Scheduled(cron = "0 3 18 * 2 *")
    public void callAsyncThreadPoolMethod(){
        Integer orderNumber = 500;
        OrderAsyncTask orderAsyncTask = new OrderAsyncTask();
        Integer threadNum = 4;
        orderAsyncTask.setThreadNum(threadNum)
                .setOrderPool(new AsyncTask(new CountDownLatch(threadNum),
                        new CyclicBarrier(threadNum)))
                .setExecutorService(Executors.newFixedThreadPool(threadNum));
        orderAsyncTask.takenOrderByThreadPool(orderNumber);
    }

}
