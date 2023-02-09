package com.JPAPrividerApp.Controller;

import com.JPAPrividerApp.Async.AsyncTaskImp.OrderAsyncTaskImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Java_IBM_Learning com.JPAPrividerApp.Controller
 *
 * @author 12645
 * @createTime 2023/2/9 18:09
 * @description
 */
@Controller
public class OrderController {
    private final OrderAsyncTaskImp orderAsyncTaskImp;

    @Autowired
    public OrderController(OrderAsyncTaskImp orderAsyncTaskImp) {
        this.orderAsyncTaskImp = orderAsyncTaskImp;
    }


    @Scheduled(cron = "0 0 0 9 2 *")
    public void callAsyncThreadPoolMethod(){
        Integer orderNumber = 1000;
        orderAsyncTaskImp.setThreadNum(4);
        orderAsyncTaskImp.takenOrderByThreadPool(orderNumber);
    }

}
