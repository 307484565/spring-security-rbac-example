package com.zt.security.dao;

import com.zt.security.model.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PermissionDao {

    @Select("select p.id, p.name, p.value " +
            "from user u " +
            "join user_role ur on u.id = ur.user_id " +
            "join role r on ur.role_id = r.id " +
            "join role_permission rp on r.id = rp.role_id " +
            "join permission p on rp.permission_id = p.id " +
            "where u.id=#{userId}")
    List<Permission> listPermissionsByUserId(Long userId);

}
