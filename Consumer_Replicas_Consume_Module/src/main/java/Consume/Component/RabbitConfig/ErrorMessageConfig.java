package Consume.Component.RabbitConfig;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.retry.MessageRecoverer;
import org.springframework.amqp.rabbit.retry.RepublishMessageRecoverer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Java_IBM_Learning Consume.Component
 *
 * @author 12645
 * @createTime 2023/3/29 12:41
 * @description
 */
@Configuration
public class ErrorMessageConfig {

    private final static String ERROR_EXCHANGE_NAME = "error.direct";
    private final static String ERROR_QUEUE_NAME = "error.queue";
    private final static String ERROR_ROUTING_KEY = "error";

    @Bean
    public DirectExchange errorDirectExchange(){
        return new DirectExchange(ERROR_EXCHANGE_NAME);
    }

    @Bean
    public Queue errorQueue(){
        return QueueBuilder.durable(ERROR_QUEUE_NAME).build();
    }

    @Bean
    public Binding errorQueueBinding(){
        return BindingBuilder.bind(errorQueue()).to(errorDirectExchange()).with(ERROR_ROUTING_KEY);
    }

    @Bean
    public MessageRecoverer errorMessageRecover(RabbitTemplate rabbitTemplate){
        return new RepublishMessageRecoverer(rabbitTemplate,ERROR_EXCHANGE_NAME,ERROR_ROUTING_KEY);
    }
}
