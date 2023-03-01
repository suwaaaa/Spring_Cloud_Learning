package com.JPAPrividerApp.Config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

@Component                                              //InstantiationAwareBeanPostProcessor
public class OrderAsyncBeanProcessor implements BeanPostProcessor {

    private Integer threadNum = 6;

    public void setThreadNum(Integer threadNum) {
        this.threadNum = threadNum;
    }

    @PostConstruct
    public void postNethod(){
        System.out.println("OrderAsyncBeanProcessor is Ready ...");
    }


    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (beanName.equals("OrderAsyncBean")){
            System.out.println("Find the Bean Name as OrderAsync and deal >>>" + beanName);
            try {
                Method setThreadNumMethod = bean.getClass().getMethod("setThreadNum",Integer.class);
                setThreadNumMethod.invoke(threadNum);
                System.out.println("OrderAsyncBean Change success >>> to " + threadNum);
            } catch (SecurityException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }
}
