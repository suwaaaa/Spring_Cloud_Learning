package Hystrix;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableHystrixDashboard        //开启服务熔断的仪表盘功能 9040 http://localhost:9040/hystrix
// 将Hystrix的stream地址输入http://localhost:9040/actuator/hystrix.stream点击monitor按钮即可监控
//较底层的服务如果出现故障，会导致连锁故障。当对特定的服务的调用达到一个阀值（Hystrix 是5秒20次） 断路器将会被打开。
//断路打开后，可用避免连锁故障，fallback方法可以直接返回一个固定值。
public class HystrixApplication {
    public static void main(String[] args) {
        SpringApplication.run( HystrixApplication.class, args);
    }
}
