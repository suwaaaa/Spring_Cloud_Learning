package Consume.Component.RabbitConfig;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Java_IBM_Learning Consume.Component.RabbitConfig
 *
 * @author 12645
 * @createTime 2023/3/29 16:16
 * @description
 */
@Configuration
public class DLXTTLConfig {

    @Bean
    public DirectExchange ttlDirectExchange(){
        return new DirectExchange("ttl.exchange");
    }

    @Bean
    public Queue ttlQueue(){
        return QueueBuilder.durable("ttl.queue")
                                .ttl(10000)//设置队列TTL时间为10秒，超过时间则 死信，发送到 死信队列
                                .deadLetterExchange("dlx.exchange")
                                .deadLetterRoutingKey("dlx")//dead key
                                .build();
    }

    @Bean
    public Binding ttlBinding(){
        return BindingBuilder.bind(ttlQueue()).to(ttlDirectExchange()).with("ttl");//route key
    }
}
