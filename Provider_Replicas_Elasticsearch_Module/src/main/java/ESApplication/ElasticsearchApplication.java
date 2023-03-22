package ESApplication;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Java_IBM_Learning ESApplication
 *
 * @author 12645
 * @createTime 2023/3/20 15:19
 * @description ES 集群
 *
 * 单机的Elasticsearch 做数据存储，会面临两个问题：海量数据存储问题，单点故障问题
 *
 * > 海量数据存储--可以将索引库从逻辑上拆分为N个分片（shard），存储到多个节点,集群越多，存储上限越高
 * > 单点故障--将分片数据在不同的节点做备份（replica），在其他的节点备份自己的数据
 */

@SpringBootApplication
@MapperScan(basePackages = "ESApplication.Mapper")
public class ElasticsearchApplication {
    public static void main(String[] args) {
        SpringApplication.run( ElasticsearchApplication.class, args);
    }

    @Bean
    public RestHighLevelClient restHighLevelClient(){
        return new RestHighLevelClient(RestClient.builder(
                HttpHost.create("http://192.168.3.128:9200")
        ));
    }

    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }
}