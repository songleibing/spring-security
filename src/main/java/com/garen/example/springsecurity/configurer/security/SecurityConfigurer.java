package com.garen.example.springsecurity.configurer.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * 安全配置
 *
 * @author leibing.song
 * @since 2019-01-28
 */
@Configuration
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationSuccessHandler successHandler;

    @Autowired
    private AuthenticationFailureHandler failureHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 配置任何URL都需要登录，登录方式为：表单登录
        // 登录页地址
        http.formLogin().loginPage("/signin")
                //登录表单提交地址。默认UsernamePasswordAuthenticationFilter只处理/login请求。
                .loginProcessingUrl("/signin/form")
                .successHandler(successHandler)
                .failureHandler(failureHandler)
                .and().authorizeRequests()
                // 配置登录页不拦截，否则会死循环
                .antMatchers("/signin", "/signin/form").permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable(); // 关闭CSRF防攻击功能
    }

}