package com.JPAPrividerApp.Controller;

import com.JPAPrividerApp.Async.AsyncTaskRunner;
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

    @Scheduled(cron = "0 * * * 3 *")
    public void callAsyncThreadPoolMethod(){
        int orderNumber = 200;
        int threadNum = 4;
        AsyncTaskRunner asyncTaskRunner = new AsyncTaskRunner(new CyclicBarrier(threadNum), new CountDownLatch(threadNum));
        asyncTaskRunner.setThreadNum(threadNum).setOrderNum(orderNumber).setExecutorService(Executors.newFixedThreadPool(threadNum));
        for (int i = 0; i < threadNum; i++)
            asyncTaskRunner.getExecutorService().submit(asyncTaskRunner);
        try {
            asyncTaskRunner.getCountDownLatch().await();
            asyncTaskRunner.getExecutorService().shutdown();
            System.out.println("线程池关闭， 处理完成");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
