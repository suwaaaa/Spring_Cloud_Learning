package com.JPAPrividerApp.Config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Java_IBM_Learning com.JPAPrividerApp.Config
 *
 * @author 12645
 * @createTime 2023/2/4 2:52
 * @description  官网中提到admin-server访问admin-client时，也是采用http Basic认证方式的
 */

@Configuration
@Slf4j
public class SpringSecurityActuatorConfig extends WebSecurityConfigurerAdapter {
    public SpringSecurityActuatorConfig() {
        log.info("SpringSecurityActuatorConfig... start");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //  这个配置只针对  /actuator/** 的请求生效
        http.antMatcher("/actuator/**")
                // /actuator/下所有请求都要认证
               .authorizeRequests().anyRequest()//.permitAll()
                // 启用httpBasic认证模式，当springboot admin-client 配置了密码时，
                // admin-server走httpbasic的认证方式来拉取client的信息
                .authenticated().and().httpBasic()
                // 禁用csrf
                .and().csrf().disable();
//        http.antMatcher("/provider02/**")
                // 放行provider02请求
//                .authorizeRequests().anyRequest().permitAll();
    }

    /*@Configuration
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    private final AdminServerProperties adminServer;

    private final SecurityProperties security;

    public MySecurityConfig(AdminServerProperties adminServer, SecurityProperties security) {
        this.adminServer = adminServer;
        this.security = security;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
        successHandler.setTargetUrlParameter("redirectTo");
        successHandler.setDefaultTargetUrl(this.adminServer.path("/"));

        http.authorizeRequests(
                (authorizeRequests) -> authorizeRequests.antMatchers(this.adminServer.path("/assets/**")).permitAll()
                        .antMatchers(this.adminServer.path("/actuator/info")).permitAll()
                        .antMatchers(this.adminServer.path("/actuator/health")).permitAll()
                        .antMatchers(this.adminServer.path("/login")).permitAll().anyRequest().authenticated()
        ).formLogin(
                (formLogin) -> formLogin.loginPage(this.adminServer.path("/login")).successHandler(successHandler).and()
        ).logout((logout) -> logout.logoutUrl(this.adminServer.path("/logout"))).httpBasic(Customizer.withDefaults())
                .csrf((csrf) -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                        .ignoringRequestMatchers(
                                new AntPathRequestMatcher(this.adminServer.path("/instances"),
                                        HttpMethod.POST.toString()),
                                new AntPathRequestMatcher(this.adminServer.path("/instances/*"),
                                        HttpMethod.DELETE.toString()),
                                new AntPathRequestMatcher(this.adminServer.path("/actuator/**"))
                        ))
                .rememberMe((rememberMe) -> rememberMe.key(UUID.randomUUID().toString()).tokenValiditySeconds(1209600));
    }

    // Required to provide UserDetailsService for "remember functionality"
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser(security.getUser().getName())
                .password("{noop}" + security.getUser().getPassword()).roles("USER");
    }
}*/
}