package Publish;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Java_IBM_Learning PACKAGE_NAME
 *
 * @author 12645
 * @createTime 2023/3/14 17:08
 * @description
 */

@RunWith(SpringRunner.class)
@SpringBootTest
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


}
