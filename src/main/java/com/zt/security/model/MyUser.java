package com.zt.security.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@TableName("user")
public class MyUser {
    @TableId(type = IdType.AUTO)
    private Long id;

    private String username;

    @JSONField(serialize = false)
    private String password;

    @TableField(exist = false)
    private List<String> roles;

    @TableField("created_at")
    private Date createdAt;

}
