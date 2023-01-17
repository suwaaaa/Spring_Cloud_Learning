package com.Config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RetryRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestConfig {

    @Bean
    @LoadBalanced // 加入ribbon的支持，用于实现负载均衡。// 在调用时，可以改为使用服务名称来访问
    public RestTemplate restTemplate() {

        return new RestTemplate();
    }

    /**
     *  myRule
     *  Description:
     *  默认为轮询策略
     *  切换其他的均衡规则

     */
    @Bean
    public IRule myRule() {
          return new RoundRobinRule();
//        return new RandomRule();
//        return new RetryRule();
    }
}
