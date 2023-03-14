package Consume.Component;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

/**
 * Java_IBM_Learning Consume.Component
 *
 * @author 12645
 * @createTime 2023/3/14 17:54
 * @description 队列一但消费完成，就会删除，没有消息回溯  Basic Queue
 *              Work Queue 模式： 多个消费者绑定一个队列，同一个消息只会被一个消费者处理， 可以设置prefetch 控制消费者预取的消息数量
 *              发布订阅 -- Topic 模式： # 代表一个或者多个， * 代表一个
 */
@Component
public class ConsumerRabbitListener {
    @RabbitListener(queues = "Simple-Queue")//Basic Queue
    public void listenSimpleQueueMessage(String message){
        System.out.println("Consumer Message Server listen message > " + message);
    }


    /*以下两个方法是工作队列，可以提高处理速度      【消费预取】默认下生产的消息会平均分配，不会因为处理时间差异导致 消息分配不均
      *                                        可以在yml文件中加入 listener.simple.prefetch =1 更为 消息处理完成才继续取 */
    @RabbitListener(queues = "Simple-Queue")//Work Queue
    public void listenWorkQueuesMessage1(String message) throws InterruptedException {
        System.out.println("Consumer Message Server 'Consumer01' listen message > " + message + LocalTime.now());
        Thread.sleep(20);
    }
    @RabbitListener(queues = "Simple-Queue")//Work Queue
    public void listenWorkQueuesMessage2(String message) throws InterruptedException {
        System.out.println("Consumer Message Server 'Consumer02' listen message > " + message + LocalTime.now());
        Thread.sleep(200);
    }



    @RabbitListener(queues = "Fanout-Consume1")//发布订阅 -- FanoutExchange
    public void listenPublishQueueMessage1(String message) {
        System.out.println("Consumer Message Server 'Fanout-Consume1' listen message > " + message);
    }
    @RabbitListener(queues = "Fanout-Consume2")//发布订阅 -- FanoutExchange
    public void listenPublishQueueMessage2(String message) {
        System.out.println("Consumer Message Server 'Fanout-Consume2' listen message > " + message);
    }


    @RabbitListener(bindings = @QueueBinding(//发布订阅 -- Direct Exchange
                                    value = @Queue(name = "Direct-Consumer1"),
                                    exchange = @Exchange(name = "Direct-Publish-Consume",type = ExchangeTypes.DIRECT),
                                    key = {"user1","user4","user5"}))//
    public void listenDirectQueueMessage1(String message) {
        System.out.println("Consumer Message Server 'Direct-Consume1' listen message - " + message);
    }
    @RabbitListener(bindings = @QueueBinding(//发布订阅 -- Direct Exchange
                                    value = @Queue(name = "Direct-Consumer2"),
                                    exchange = @Exchange(name = "Direct-Publish-Consume",type = ExchangeTypes.DIRECT),
                                    key = {"user2","user3","user5"}))//
    public void listenDirectQueueMessage2(String message) {
        System.out.println("Consumer Message Server 'Direct-Consume2' listen message - " + message);
    }



    @RabbitListener(bindings = @QueueBinding(//发布订阅 -- Topic Exchange
                                 value = @Queue(value = "Topic-Consumer1"),
                                exchange = @Exchange(value = "Topic-Publish-Consume", type = ExchangeTypes.TOPIC),
                                key = "China.#"
    ))
    public void listenTopicQueueMessage1(String message){
        System.out.println("Consumer Message Server 'Topic-Consumer1' listen message - " + message);
    }
    @RabbitListener(bindings = @QueueBinding(//发布订阅 -- Topic Exchange
            value = @Queue(value = "Topic-Consumer2"),
            exchange = @Exchange(value = "Topic-Publish-Consume", type = ExchangeTypes.TOPIC),
            key = "#.news"
    ))
    public void listenTopicQueueMessage2(String message){
        System.out.println("Consumer Message Server 'Topic-Consumer2' listen message - " + message);
    }
}
