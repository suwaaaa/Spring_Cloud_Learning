package com.JPAPrividerApp.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Java_IBM_Learning com.JPAPrividerApp.Config
 *
 * @author 12645
 * @createTime 2023/2/4 12:41
 * @description client与server域名存在不一样的情况，顺便把跨域也解决掉，增加跨域配置类
 */

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowCredentials(true)
                .allowedHeaders("*")
                .allowedOrigins("*")
                .allowedMethods("*");

    }
}
