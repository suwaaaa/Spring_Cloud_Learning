package com.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Java_IBM_Learning com.Config
 *
 * @author 12645
 * @createTime 2023/2/3 22:03
 * @description
 */

@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable(); //关闭csrf
//        super.configure(http); //开启认证
        http       .authorizeRequests().anyRequest().authenticated()
                // 启用httpBasic认证模式，当springboot admin-client 配置了密码时，
                // admin-server走httpbasic的认证方式来拉取client的信息
                .and().httpBasic()
                // 禁用csrf
                .and().csrf().disable();
    }
}

