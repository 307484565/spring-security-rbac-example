package com.zt.security.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zt.security.model.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RoleDao extends BaseMapper<Role> {

}
