import com.rabbitmq.client.*;
import org.junit.Test;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeoutException;

/**
 * Java_IBM_Learning PACKAGE_NAME
 *
 * @author 12645
 * @createTime 2023/3/14 16:14
 * @description 简单队列，官方HelloWorld 模型 Host 在Vmware 虚拟机
 */

public class RabbitMQPublisherTest1 {
    @Test
    public void TestForSimplePublisher1() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();//get Connection //当前的factory设置可以编写在application.properties
        factory.setHost("192.168.3.128");
        factory.setPort(5672);
        factory.setVirtualHost("/");
        factory.setUsername("Javon");
        factory.setPassword("12345");
        Connection connection = factory.newConnection();

        Channel channel = connection.createChannel();//create channel
        String  queueName = "Simple-Queue";
        channel.queueDeclare(queueName,false,false,false,null);

        String publishMessage = "Hello  ! Now is " + new Date().toString();//publish message to channel
        channel.basicPublish("",queueName,null,publishMessage.getBytes());
        System.out.println("Message has sent !");

        channel.close();//close the channel & connection
        connection.close();
    }

    @Test
    public void TestForSimpleConsumer1() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();//get Connection
        factory.setHost("192.168.3.128");
        factory.setPort(5672);
        factory.setVirtualHost("/");
        factory.setUsername("Javon");
        factory.setPassword("12345");
        Connection connection = factory.newConnection();

        Channel channel = connection.createChannel();//create channel
        String  queueName = "Simple-Queue";
        channel.queueDeclare(queueName,false,false,false,null);

        channel.basicConsume(queueName, true, new DefaultConsumer(channel){//consume
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) {
//                super.handleDelivery(consumerTag, envelope, properties, body);
                System.out.println("Consumer handle message >> " + new String(body));// get consume message
            }
        });
        System.out.println("Ready to consume the message form Queue! >>> ");
        channel.close();//close the channel & connection
        connection.close();
    }


}
