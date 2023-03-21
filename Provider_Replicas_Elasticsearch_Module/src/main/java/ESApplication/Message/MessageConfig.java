package ESApplication.Message;

import ESApplication.Constants.HotelMqConstants;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Java_IBM_Learning ESApplication.MessageListener
 *
 * @author 12645
 * @createTime 2023/3/21 22:25
 * @description new Exchange --> new Queue --> Binding Queue to Exchange
 */
@Configuration
public class MessageConfig {

    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange(HotelMqConstants.EXCHANGE_NAME,true,false);
    }

    @Bean
    public Queue insertQueue(){
        return new Queue(HotelMqConstants.INSERT_QUEUE_NAME);
    }

    @Bean
    public Queue deleteQueue(){
        return new Queue(HotelMqConstants.DELETE_QUEUE_NAME);
    }

    @Bean
    public Binding insertBindingQueue(){
        return BindingBuilder.bind(insertQueue()).to(topicExchange()).with(HotelMqConstants.INSERT_KEY);
    }

    @Bean
    public Binding deleteBindingQueue(){
        return BindingBuilder.bind(deleteQueue()).to(topicExchange()).with(HotelMqConstants.DELETE_KEY);
    }
}
