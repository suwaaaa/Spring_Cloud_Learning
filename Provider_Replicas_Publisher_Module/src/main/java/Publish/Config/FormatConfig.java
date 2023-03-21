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

    /*
    *解决Rabbit MQ 中，发送队列中，生产者发送Long 类型到队列， 消费者拿到不同值的Long 数值的问题：
    * 1. 修改JSON 在分布式传输中 对象序列化不同的问题
    * 2. 需要同时修改数据库 字符集&characterEncoding=utf-8 解决保存乱码问题
    */
    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }
}
