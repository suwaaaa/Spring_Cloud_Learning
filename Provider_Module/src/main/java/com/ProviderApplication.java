package com;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;

@EnableEurekaClient//  启动Eureka客户端支持  Port: 9020
@SpringBootApplication
@EnableConfigurationProperties
@PropertySource({"classpath:application.properties"})
public class ProviderApplication {
      public static void main(String[] args) {
          SpringApplication.run( ProviderApplication.class, args);
      }

      @Value("${web.file.limitSize}")
      private Integer fileLimit;

      @Value("${web.file.maxFileRequest}")
      private Integer maxFileRequest;

      @Bean
      public MultipartConfigElement multipartConfigElement() {
          MultipartConfigFactory factory = new MultipartConfigFactory();
          //单个文件最大
          factory.setMaxFileSize(DataSize.ofMegabytes(fileLimit)); //KB,MB
          // 设置总上传数据总大小
          factory.setMaxRequestSize(DataSize.ofKilobytes(maxFileRequest));
          return factory.createMultipartConfig();
        }
}


