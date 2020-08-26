package com.zt.security.util;

import com.zt.security.config.Consts;
import com.zt.security.model.MyUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Function;

@Slf4j
@Component
public class TokenUtil {

    @Value("${token.signingKey}")
    private String signingKey;

    /**
     * token过期时间，单位：秒
     */
    @Value("${token.expiration}")
    private Long expiration;

    /**
     * 根据 TokenDetail 生成 Token
     *
     * @param myUser
     * @return
     */
    public String generateToken(MyUser myUser) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("sub", myUser.getUsername());
        claims.put("created", myUser.getCreatedAt());
        claims.put("scopes", Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN")));
        return this.generateToken(claims);
    }

    public String getUsernameFromToken(String token) {
        return getAllClaimsFromToken(token).getSubject();
    }

    public Date getExpirationDateFromToken(String token) {
        return getAllClaimsFromToken(token).getExpiration();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }


    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(signingKey)
                .parseClaimsJws(token).getBody();
    }

    /**
     * 根据 claims 生成 Token
     *
     * @param claims
     * @return
     */
    private String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
                .signWith(SignatureAlgorithm.HS512, signingKey)
                .compact();
    }

    /**
     * 获得当前时间
     *
     * @return
     */
    private Date generateCurrentDate() {
        return new Date(System.currentTimeMillis());
    }

}