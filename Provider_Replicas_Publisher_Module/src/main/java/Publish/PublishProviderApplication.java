package Publish;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Java_IBM_Learning Publish
 *
 * @author 12645
 * @createTime 2023/3/14 17:03
 * @description  Rabbit MQ link: http://192.168.3.128:15672/#/exchanges (Should oprn Vmware 15 Pro and running CentOS 7 Docker)
 */

@SpringBootApplication
@MapperScan(basePackages = "Publish.mapper")
public class PublishProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run( PublishProviderApplication.class, args);
    }

    // 最新版
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }
}
