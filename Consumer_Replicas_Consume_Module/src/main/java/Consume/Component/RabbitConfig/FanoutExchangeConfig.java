package Consume.Component.RabbitConfig;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Java_IBM_Learning Consume.Component
 *
 * @author 12645
 * @createTime 2023/3/14 19:13
 * @description  发布订阅 -- FanoutExchange   {Exchange, Queue, Binding}
 */
@Configuration
public class FanoutExchangeConfig {
    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange("Fanout-Publish-Consume",true,false );
    }

    @Bean
    public Queue fanoutQueue1(){
        return QueueBuilder.durable("Fanout-Consume1").build();// ==  return new Queue("Fanout-Consume1",true);
    }

    @Bean
    public Binding fanoutBing1(FanoutExchange fanoutExchange, Queue fanoutQueue1){
        return BindingBuilder.bind(fanoutQueue1).to(fanoutExchange);
    }

    @Bean
    public Queue fanoutQueue2(){
        return new Queue("Fanout-Consume2");
    }

    @Bean
    public Binding fanoutBing2(FanoutExchange fanoutExchange, Queue fanoutQueue2){
        return BindingBuilder.bind(fanoutQueue2).to(fanoutExchange);
    }


    @Bean
    public Queue errorFanoutQueue(){
        return new Queue("Fanout-Consume-Error");
    }

    @Bean
    public Binding fanoutBing2Error(FanoutExchange fanoutExchange, Queue errorFanoutQueue){
        return BindingBuilder.bind(errorFanoutQueue).to(fanoutExchange);
    }
}
