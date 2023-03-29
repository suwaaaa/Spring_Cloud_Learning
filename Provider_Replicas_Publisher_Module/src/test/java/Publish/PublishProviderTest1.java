package Publish;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.UUID;

/**
 * Java_IBM_Learning PACKAGE_NAME
 *
 * @author 12645
 * @createTime 2023/3/14 17:08
 * @description
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class PublishProviderTest1 {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test//Basic Queue
    public void testSimpleQueue(){
        String queueName = "Simple-Queue";
        String message = "Hello, this is Provider Publish Server";
        rabbitTemplate.convertAndSend(queueName, message);
        System.out.println("testSimpleQueue completed");
    }

    @Test//Work Queue
    public void workQueues() throws InterruptedException {
        String queueName = "Simple-Queue";
        String message = "Hello, this is Provider Publish Server, times : ";
        for (int i = 1; i <= 50; i++) {
            rabbitTemplate.convertAndSend(queueName, message+i);
            Thread.sleep(20);
        }
        System.out.println("testSimpleQueue completed");
    }

    @Test//发布订阅 -- FanoutExchange
    public void testSendFanoutMessagePublish(){
        String exchangeName = "Fanout-Publish-Consume";
        String messagePublish = "Provider Publish Server >> publish message > Hello, Fanout-Publish-Consume";
        rabbitTemplate.convertAndSend(exchangeName,"",messagePublish);
    }


    @Test//发布订阅 -- Direct Exchange
    public void testDirectMessagePublishUser1(){
        String exchangeName = "Direct-Publish-Consume";
        String messagePublish = "Provider Publish Server >> publish message > Hello, Direct-Publish-Consume";
        rabbitTemplate.convertAndSend(exchangeName,"user1",messagePublish);
    }
    @Test//发布订阅 -- Direct Exchange
    public void testDirectMessagePublishUser5(){
        String exchangeName = "Direct-Publish-Consume";
        String messagePublish = "Provider Publish Server >> publish message > Hello, Direct-Publish-Consume";
        rabbitTemplate.convertAndSend(exchangeName,"user5",messagePublish);
    }


    @Test//发布订阅 -- Topic Exchange
    public void testTopicMessagePublish1(){
        String exchangeName = "Topic-Publish-Consume";
        String messagePublish = "Provider Publish Server >> publish message > Hello, Topic-Publish-Consume";
        rabbitTemplate.convertAndSend(exchangeName,"China.news",messagePublish);
    }





    @Test//FanoutExchange 测试发布者确认功能-ReturnCallback
    public void publisherReturnCallbackTest(){
        String exchangeName = "Fanout-Publish-Consume";
        String messagePublish = "Provider Publish Server >> publish message > Hello, Fanout-Publish-Consume：ReturnCallback";
        //消息持久化
        MessageBuilder.withBody(messagePublish.getBytes(StandardCharsets.UTF_8)).setDeliveryMode(MessageDeliveryMode.PERSISTENT).build();
        // Prepare CorrelationData
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        // ReturnCallback
        correlationData.getFuture().addCallback(result -> { //success call back function
            assert result != null;
            if (result.isAck()) {//Ack
                log.info("Message deliver to exchanger success !, message ID:{}",correlationData.getId());
            }else {//NAck
                log.error("Message deliver to exchanger fail !, message ID:{}, fail reason:{}",correlationData.getId(),result.getReason());
                //you can resend the message to exchange
                //......
            }
        },ex -> {//Exception  //exception call back function
            log.error("Message deliver to exchanger occur Exception !, exception message ID:{}", ex);
        });
        rabbitTemplate.convertAndSend(exchangeName,"callback",messagePublish, correlationData);
    }


    @Test//FanoutExchange 测试TTL DLX
    public void publisherTTLTest(){
        String exchangeName = "ttl.exchange";
        String messagePublish = "Provider Publish Server >> publish message > Hello, ttl.exchange：TTL Message";
        //消息持久化
        MessageBuilder.withBody(messagePublish.getBytes(StandardCharsets.UTF_8))
                .setDeliveryMode(MessageDeliveryMode.PERSISTENT)
                .setExpiration("5000")//设置发送消息的TTL
                .build();
        rabbitTemplate.convertAndSend(exchangeName,"ttl",messagePublish);
        log.info("Had Sent");
    }
    @Test//FanoutExchange 测试 DelayExchange 插件   全局搜索【DelayExchange插件】  Should download plugins！
    public void publisherTTLDelayedMsgTest(){
        String exchangeName = "ttl.exchange";
        String messagePublish = "Provider Publish Server >> publish message > Hello, ttl.exchange：Delayed Message （DelayExchange Plugins）";
        //消息持久化
        MessageBuilder.withBody(messagePublish.getBytes(StandardCharsets.UTF_8))
                .setDeliveryMode(MessageDeliveryMode.PERSISTENT)
                .setHeader("x-delay",5000)//设置消息延迟发送头部信息，由 【DelayExchange插件】 完成
                .build();
        rabbitTemplate.convertAndSend(exchangeName,"ttl",messagePublish);
        log.info("Had Sent");
    }
}
