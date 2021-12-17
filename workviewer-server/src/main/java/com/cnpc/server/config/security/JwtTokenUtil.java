package com.cnpc.server.config.security;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: JwtToken工具类
 * @ClassName: JwtTokenUtil
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
     * @Description: 根据用户信息生成TOKEN
     * @Param: userDetails
     * @Author: yangg19
     * @UpdateTime: 2021/12/17 13:13
     * @Return: java.lang.String
     * @Throws:
     */
    public String generateToken(UserDetails userDetails) {

        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
        claims.put(CLAIM_KEY_CREATED, new Date());

        return generateToken(claims);

    }

    /**
     * @Description: 从token中获取登录用户名
     * @Param: token
     * @Author: yangg19
     * @UpdateTime: 2021/12/17 13:13
     * @Return: java.lang.String
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
     * @Description: 验证token是否有效
     * @Param: token
     * @Param: userDetails
     * @Author: yangg19
     * @UpdateTime: 2021/12/17 11:03
     * @Return: boolean
     * @Throws:
     */
    public boolean validateToken(String token, UserDetails userDetails) {
        String username = getUserNameFromToken(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    /**
     * @Description: 判断token是否可以刷新
     * @Param: token
     * @Author: yangg19
     * @UpdateTime: 2021/12/17 13:25
     * @Return: boolean
     * @Throws:
     */
    public boolean canRefresh(String token) {
        return !isTokenExpired(token);
    }

    /**
     * @Description: 刷新token
     * @Param: token
     * @Author: yangg19
     * @UpdateTime: 2021/12/17 13:28
     * @Return: java.lang.String
     * @Throws:
     */
    public String refreshToken(String token) {
        Claims claims = getClaimsFromToken(token);
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }

    /**
     * @description: 判断token是否失效
     * @param: token
     * @author: yangg19
     * @updateTime: 2021/12/17 10:40
     * @return: boolean
     * @throws:
     */

    private boolean isTokenExpired(String token) {
        Date expiredData = getExpiredDataFromToken(token);
        return expiredData.before(new Date());
    }

    /**
     * @description: 从token中获取失效时间
     * @param: token
     * @author: yangg19
     * @updateTime: 2021/12/17 10:41
     * @return: java.util.Date
     * @throws:
     */
    private Date getExpiredDataFromToken(String token) {

        Claims claims = getClaimsFromToken(token);
        return claims.getExpiration();
    }


    /**
     * @description: 从TOKEN中获取荷载
     * @param: token
     * @author: yangg19
     * @updateTime: 2021/12/17 10:41
     * @return: io.jsonwebtoken.Claims
     * @throws:
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
     * @Description: 根据荷载生成JWT TOKEN
     * @Param: claims
     * @Author: yangg19
     * @UpdateTime: 2021/12/17 13:14
     * @Return: java.lang.String
     * @Throws:
     */
    private String generateToken(Map<String, Object> claims) {

        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.ES256, secret)
                .compact();
    }

    /**
     * @Description: 生成TOKEN失效时间
     * @Params:
     * @Author: yangg19
     * @UpdateTime: 2021/12/17 10:50
     * @Return:
     * @Throws:
     */

    private Date generateExpirationDate() {


        return new Date(System.currentTimeMillis() + expiration * 1000);

    }
}
