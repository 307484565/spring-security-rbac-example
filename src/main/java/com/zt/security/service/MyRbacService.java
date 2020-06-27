package com.zt.security.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import javax.servlet.http.HttpServletRequest;

/**
 * 自定义权限匹配策略
 */
//@Component
public class MyRbacService {
    //返回结果为验证是否通过，不通过跳回登录页
    public boolean findAuthority(HttpServletRequest request, Authentication authentication) {
        Object principal = authentication.getPrincipal();
        //只有登录后才是userDetails对象，未登录时是字符串
        if(principal instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) principal;
            String username = userDetails.getUsername();
            System.out.println(username + " | " + authentication.getAuthorities());
            //之后进行权限判断
        } else {
            //跳回登录页
            return false;
        }
        return true;
    }
}
