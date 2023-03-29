package Publish.Config;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

/**
 * Java_IBM_Learning Publish.Config
 *
 * @author 12645
 * @createTime 2023/3/29 10:48
 * @description
 */
@Configuration
@Slf4j
public class RabbitQueueFallBackCallConfig implements ApplicationContextAware {
    @Override
    public void setApplicationContext(@SuppressWarnings("NullableProblems") ApplicationContext applicationContext) throws BeansException {
        //获取RabbitTemplate对象
        RabbitTemplate rabbitTemplate = applicationContext.getBean(RabbitTemplate.class);
        //配置ReturnCallback
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
            //记录日志
            log.error("消息队列发送失败，响应码{}，失败原因{}，交换机{}，路由key{}，消息{}",
                    replyCode,replyText,exchange,routingKey,message.toString());
            //可以选择 失败后重新回发消息
        });
    }
}
