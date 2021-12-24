package com.cnpc.server.config.security;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JwtToken工具类
 *
 * @Author: yangg19
 * @Date: 2021/12/3 4:41 下午
 * @Version 1.0
 */

@Component
public class JwtTokenUtil {

    // 用户名
    public static final String CLAIM_KEY_USERNAME = "sub";

    // JWT创建时间
    public static final String CLAIM_KEY_CREATED = "created";

    // Jwt密钥 通过value注解去配置信息application.yml中获取
    @Value("${jwt.secret}")
    private String secret;

    // Jwt失效时间 通过value注解去配置信息application.yml中获取
    @Value("${jwt.expiration}")
    private Long expiration;

    /**
     * 根据用户信息生成TOKEN
     *
     * @Params: [userDetails]
     * @Return: java.lang.String
     * @Author: yangg19
     * @UpdateTime: 2021/12/20 9:50
     * @Throws:
     */
    public String generateToken(UserDetails userDetails) {

        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
        claims.put(CLAIM_KEY_CREATED, new Date());

        return generateToken(claims);

    }

    /**
     * 从token中获取登录用户名
     *
     * @Params: [token]
     * @Return: java.lang.String
     * @Author: yangg19
     * @UpdateTime: 2021/12/20 9:54
     * @Throws:
     */
    public String getUserNameFromToken(String token) {

        String username;
        try {
            Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    /**
     * 验证token是否有效
     *
     * @Params: [token, userDetails]
     * @Return: boolean
     * @Author: yangg19
     * @UpdateTime: 2021/12/20 9:54
     * @Throws:
     */
    public boolean validateToken(String token, UserDetails userDetails) {
        String username = getUserNameFromToken(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    /**
     * 判断token是否可以刷新
     *
     * @Params: [token]
     * @Return: boolean
     * @Author: yangg19
     * @UpdateTime: 2021/12/20 9:54
     * @Throws:
     */
    public boolean canRefresh(String token) {
        return !isTokenExpired(token);
    }

    /**
     * 刷新token
     *
     * @Params: [token]
     * @Return: java.lang.String
     * @Author: yangg19
     * @UpdateTime: 2021/12/20 9:55
     * @Throws:
     */
    public String refreshToken(String token) {
        Claims claims = getClaimsFromToken(token);
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }

    /**
     * 判断token是否失效
     *
     * @Params: [token]
     * @Return: boolean
     * @Author: yangg19
     * @UpdateTime: 2021/12/20 9:55
     * @Throws:
     */

    private boolean isTokenExpired(String token) {
        Date expiredData = getExpiredDataFromToken(token);
        return expiredData.before(new Date());
    }

    /**
     * 从token中获取失效时间
     *
     * @Params: [token]
     * @Return: java.util.Date
     * @Author: yangg19
     * @UpdateTime: 2021/12/20 9:55
     * @Throws:
     */
    private Date getExpiredDataFromToken(String token) {

        Claims claims = getClaimsFromToken(token);
        return claims.getExpiration();
    }


    /**
     * 从TOKEN中获取荷载
     *
     * @Params: [token]
     * @Return: io.jsonwebtoken.Claims
     * @Author: yangg19
     * @UpdateTime: 2021/12/20 9:55
     * @Throws:
     */
    private Claims getClaimsFromToken(String token) {

        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            e.printStackTrace();
        }
        return claims;

    }

    /**
     * 根据荷载生成JWT TOKEN
     *
     * @Params: [claims]
     * @Return: java.lang.String
     * @Author: yangg19
     * @UpdateTime: 2021/12/20 9:55
     * @Throws:
     */
    private String generateToken(Map<String, Object> claims) {

        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /**
     * 生成TOKEN失效时间
     *
     * @Params: []
     * @Return: java.util.Date
     * @Author: yangg19
     * @UpdateTime: 2021/12/20 9:55
     * @Throws:
     */

    private Date generateExpirationDate() {


        return new Date(System.currentTimeMillis() + expiration * 1000);

    }
}
