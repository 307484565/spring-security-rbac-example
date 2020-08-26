package com.zt.security;

import com.alibaba.fastjson.JSON;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;

/**
 * desc
 *
 * @author zhangtao
 */
public class JwtTest {

    public static void main(String[] args) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("sub", "zt");
        map.put("exp", System.currentTimeMillis() + 60 * 10 * 1000);
        map.put("iat", System.currentTimeMillis());

        JwtBuilder jwtBuilder = Jwts.builder().setClaims(map).signWith(SignatureAlgorithm.HS256, "sign");
        System.out.println(jwtBuilder.compact());
        Jwt parse = Jwts.parser().setSigningKey("secret").parse(jwtBuilder.compact());
        System.out.println(JSON.toJSONString(parse));


    }

}
