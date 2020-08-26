package com.zt.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
//启用注解, prePostEnabled=启用preAuthorize等表达式注解
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin()
                .and().authorizeRequests()
                //跨域处理
                .antMatchers(HttpMethod.OPTIONS, "/**").anonymous()
                .antMatchers("/token/*").permitAll()
                .anyRequest().authenticated()
                //禁用spring自带跨域处理
                .and().csrf().disable()
                //不创建和使用session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                //addBefore, jwtFilter必须指定加载顺序
                .and().addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                //自定义无权限响应
                .exceptionHandling().accessDeniedHandler((request, response, authException) -> {
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Cache-Control", "no-cache");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            response.getWriter().println("无权限访问");
            response.getWriter().flush();
        });
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
