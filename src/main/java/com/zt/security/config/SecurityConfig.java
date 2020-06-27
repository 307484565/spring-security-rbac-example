package com.zt.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
//启用注解, prePostEnabled=启用preAuthorize等表达式注解
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().and().authorizeRequests().anyRequest().authenticated()
            //自定义无权限响应
            .and().exceptionHandling().accessDeniedHandler((request, response, authException) -> {
                response.setHeader("Access-Control-Allow-Origin", "*");
                response.setHeader("Cache-Control","no-cache");
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json");
                response.getWriter().println("无权限访问");
                response.getWriter().flush();
        });
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
