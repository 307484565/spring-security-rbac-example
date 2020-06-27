package com.zt.security.controller;


import com.alibaba.fastjson.JSON;
import com.zt.security.bo.MyUserDetails;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class IndexController {

    @RequestMapping("/")
    public String user(Model  model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MyUserDetails myUserDetails = (MyUserDetails) authentication.getPrincipal();
        model.addAttribute("user", JSON.toJSONString(myUserDetails.getMyUser()));
        model.addAttribute("permissions", JSON.toJSONString(myUserDetails.getPermissions()));
        return "index";
    }

    @GetMapping("/user/list")
    @PreAuthorize("hasAuthority('user:list')")
    public void userList(HttpServletResponse response) throws IOException {
        response.getWriter().write("permission user:list ");
    }

    @GetMapping("/user/update")
    @PreAuthorize("hasAuthority('user:update')")
    public void userUpdate(HttpServletResponse response) throws IOException {
        response.getWriter().write("permission user:update ");
    }

}
