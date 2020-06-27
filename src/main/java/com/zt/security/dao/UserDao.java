package com.zt.security.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zt.security.model.MyUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserDao extends BaseMapper<MyUser> {

}
