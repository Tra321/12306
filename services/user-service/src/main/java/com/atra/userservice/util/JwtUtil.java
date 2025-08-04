package com.atra.userservice.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * JWT 工具类，用于生成、解析和验证 Token
 *
 * @author Cline
 */
@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;

    private SecretKey key;

    /**
     * 在构造函数之后执行，用于初始化 SecretKey
     */
    @jakarta.annotation.PostConstruct
    public void init() {
        byte[] keyBytes = Base64.getDecoder().decode(secret);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * 从 Token 中提取用户名
     *
     * @param token JWT
     * @return 用户名
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * 从 Token 中提取过期日期
     *
     * @param token JWT
     * @return 过期日期
     */
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * 提取 Token 中的单个 Claim
     *
     * @param token          JWT
     * @param claimsResolver Claim 解析函数
     * @param <T>            Claim 的类型
     * @return Claim
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * 提取 Token 中的所有 Claims
     *
     * @param token JWT
     * @return Claims
     */
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }

    /**
     * 检查 Token 是否已过期
     *
     * @param token JWT
     * @return 如果已过期则返回 true
     */
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * 为指定用户生成 Token
     *
     * @param username 用户名
     * @return JWT
     */
    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, username);
    }

    /**
     * 创建 Token
     *
     * @param claims  自定义的 Claims
     * @param subject 用户名
     * @return JWT
     */
    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * 验证 Token 是否有效
     *
     * @param token    JWT
     * @param username 用户名
     * @return 如果有效则返回 true
     */
    public Boolean validateToken(String token, String username) {
        final String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }
}
