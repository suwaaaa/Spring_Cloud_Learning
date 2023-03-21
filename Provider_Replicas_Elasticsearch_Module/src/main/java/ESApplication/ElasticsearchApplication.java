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
 * @description
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