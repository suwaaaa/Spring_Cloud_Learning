package Publish.Config;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Java_IBM_Learning Publish.Config
 *
 * @author 12645
 * @createTime 2023/3/14 20:37
 * @description Spring 对于消息对象的处理是org.springframework.amqp.support.MessageConverter处理的。默认实现是SimpleMessageConverter，基于JDK的Object outPutStream完成序列化
 *     * 解决Rabbit MQ 里面传输对象 被默认序列化 的问题， 需要引入jackson2Json （见pom以及@Bean）
 */
@Configuration
public class FormatConfig {

    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }
}
