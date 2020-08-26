package com.zt.security.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zt.security.bo.MyUserDetails;
import com.zt.security.dao.PermissionDao;
import com.zt.security.dao.UserDao;
import com.zt.security.model.MyUser;
import com.zt.security.model.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Resource
    private UserDao userDao;
    @Resource
    private PermissionDao permissionDao;

    @Override
    public MyUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUser user = userDao.selectOne(Wrappers.<MyUser>lambdaQuery()
                .eq(MyUser::getUsername, username));
        if(user == null) {
            throw new UsernameNotFoundException("username not found");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        List<Permission> permissions = permissionDao.listPermissionsByUserId(user.getId());
        MyUserDetails myUserDetails = new MyUserDetails();
        myUserDetails.setMyUser(user);
        myUserDetails.setPermissions(permissions);
        return myUserDetails;
    }
}
