package com.JPAPrividerApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

//exclude 去除spring security 的权限校验
@SpringBootApplication//(exclude = {SecurityAutoConfiguration.class, ManagementWebSecurityAutoConfiguration.class})
@EnableEurekaClient
@EnableGlobalMethodSecurity(prePostEnabled = true) //组件需要“org.springframework.security.configannotation”类型的bean。找不到的ObjectPostProcessor“”。
@EnableScheduling // 启动类里面 @EnableScheduling开启定时任务，自动扫描
@EnableAsync // 异步任务,启动类里面使用@EnableAsync注解开启功能，自动扫描
public class JpaProvider02Application {  // Port: 9025
    public static void main(String[] args) {
        SpringApplication.run(JpaProvider02Application.class, args);
    }

//    @EnableWebSecurity//禁止跨域 同SpringSecurityActuatorConfig
//    static class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//        @Override
//        protected void configure(HttpSecurity http) throws Exception {
//            http.csrf().disable();
//            http.authorizeRequests().anyRequest().authenticated().and().httpBasic(); //开启认证
//        }
//    }
}


