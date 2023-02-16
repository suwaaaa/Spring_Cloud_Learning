package com.JPAPrividerApp.Controller;

import com.JPAPrividerApp.Async.AsyncTaskImp.OrderAsyncTaskImp;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

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


    @Scheduled(cron = "0 10 18 * 2 *")
    public void callAsyncThreadPoolMethod(){
        Integer orderNumber = 500;
        String property = "threadNum";
        MutablePropertyValues pvs = new MutablePropertyValues();
        pvs.add(property, 6);//TODO  修改Bean的属性值
        BeanWrapper wrapper = PropertyAccessorFactory.forBeanPropertyAccess(this.orderAsyncTaskImp);
        wrapper.setPropertyValues(pvs);
        orderAsyncTaskImp.takenOrderByThreadPool(orderNumber);
    }

}
