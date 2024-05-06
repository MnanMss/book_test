package cn.mila.book_test_master.core.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * JWT 工具类
 *
 * @author mila
 * @date 2024/5/5 下午8:39
 */
@ConditionalOnProperty("book.jwt.secret")
@Component
@Slf4j
public class JwtUtils {

    /**
     * 注入JWT密钥
     */
    @Value("${book.jwt.secret}")
    private String secret;

    /**
     * 系统标识头常量
     */
    private static final String HEADER_SYSTEM_KEY = "systemKeyHeader";

    /**
     * 根据用户ID生成JWT
     *
     * @param userId    用户ID
     * @param systemKey 系统标识
     * @return JWT
     */

    public String generateToken(Long userId, String systemKey) {
        return Jwts.builder()
            .setHeaderParam(HEADER_SYSTEM_KEY, systemKey)
            .setSubject(userId.toString())
            .signWith(Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8)))
            .compact();
    }

    /**
     * 解析JWT返回用户ID
     *
     * @param token     JWT
     * @param systemKey 系统标识
     * @return 用户ID
     */
    public Long parseToken(String token, String systemKey) {
        Jws<Claims> claimsJws;
        try {
            claimsJws = Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8)))
                .build()
                .parseClaimsJws(token);
            // 可以信任这个jwt
            // 判断这个jwt是否属于这个系统
            if (Objects.equals(claimsJws.getHeader().get(HEADER_SYSTEM_KEY), systemKey)) {
                return Long.parseLong(claimsJws.getBody().getSubject());
            }
        } catch (JwtException e) {
            // 不可信任这个jwt
            log.warn("JWT解析失败：{}", token);
        }
        return null;
    }
}
