package com.JPAPrividerApp.Async.AsyncTaskImp;

import com.JPAPrividerApp.Async.AsyncTask;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.concurrent.*;

/**
 * Java_IBM_Learning com.JPAPrividerApp.Async.AsyncTaskImp
 *
 * @author 12645
 * @createTime 2023/2/9 17:58
 * @description
 */
@Component
@Data
public class OrderAsyncTaskImp {

    private Integer threadNum = 4;    //模拟多个线程
    private CyclicBarrier cyclicBarrier = new CyclicBarrier(threadNum);
    private CountDownLatch countDownLatch = new CountDownLatch(threadNum);  // 用于统计 执行时长
    private AsyncTask orderPool = new AsyncTask(countDownLatch, cyclicBarrier);
    private ExecutorService executorService = Executors.newFixedThreadPool(threadNum);

    public void takenOrderByThreadPool(@NotNull Integer orderNumber){
        AsyncTask.setOrderNum(orderNumber);
        for (int i = 0; i < threadNum; i++) {   //此处 设置数值  受限于 线程池中的数量
            executorService.submit(orderPool);
        }
        try {
            countDownLatch.await();
            executorService.shutdown();
            System.out.println("线程池关闭， 处理完成");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
