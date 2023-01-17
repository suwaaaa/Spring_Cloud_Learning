package com.Controller;

import com.Service.ServiceImp.ConsumerServiceImp;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ConsumerController {

    private final ConsumerServiceImp consumerServiceImp;
    @Autowired
    public ConsumerController(ConsumerServiceImp consumerServiceImp) {
        this.consumerServiceImp = consumerServiceImp;
    }

    @GetMapping("/")
    public String welcome() {
        return consumerServiceImp.welcome();
    }


    @RequestMapping("/consume/hello")
    @HystrixCommand(fallbackMethod = "feedback")
    //	  ,commandProperties ={@HystrixProperty(name = "execution.ioslation.thread.timeoutInMillisecond",value="1500")}设置超时的时间为1.5秒，默认为1000毫秒，即1秒
    //    ,ignoreExceptions = Exception.class忽略抛出的异常 -- 忽略多个异常ignoreExceptions = {ParamErrorException.class, BusinessTypeException.class})
    public String hellString() {
        return consumerServiceImp.hellString();
    }

    @RequestMapping("/web/hystrix")
    @HystrixCommand(fallbackMethod = "feedback")
    public String hystrix() {
        return consumerServiceImp.hystrix();
    }

    String feedback(Throwable throwable) { //融断的回调方法，也就是降级方法
        System.out.println("异常：==== "+throwable.getMessage());
        return "发现Consumer Error" + "+++使用Hystrix服务熔断+++";
    }



}
