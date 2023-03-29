package Consume.Component.Listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Java_IBM_Learning Consume.Component
 *
 * @author 12645
 * @createTime 2023/3/29 16:10
 * @description
 */

@Slf4j
@Component
public class DeadLetterExchangeListener {

    @RabbitListener(bindings = @QueueBinding( // TTL 延迟队列
                                    value = @Queue(name = "dlx.queue", durable = "true"),
                                    exchange = @Exchange(name = "dlx.exchange"),
                                    key = "dlx"
    ))
    public void listenDeadExchange(String message){
        log.info("receive Dead Letter : " + message);
        System.out.println("End ");
    }

    @RabbitListener(bindings = @QueueBinding(  // delayed = "true" 延迟队列，有 【DelayExchange插件】
            value = @Queue(name = "dlx.queue", durable = "true"),
            exchange = @Exchange(name = "dlx.exchange",delayed = "true"),
            key = "dlx"
    ))
    public void listenDeadExchangeDelayed(String message){
        log.info("receive Dead Letter : " + message);
        System.out.println("End ");
    }
}
