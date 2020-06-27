package com.zt.security.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.List;

@Data
@TableName("role")
public class Role {
    @TableId(type = IdType.AUTO)
    private Long id;

    private String name;

    private List<Permission> permissions;

}
