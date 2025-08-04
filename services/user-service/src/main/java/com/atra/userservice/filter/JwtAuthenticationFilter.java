package com.atra.userservice.filter;

import com.atra.userservice.util.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * JWT 认证过滤器
 *
 * @author Cline
 */
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String username;

        // 1. 检查 Authorization 请求头是否存在且格式正确
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response); // 如果不合法，直接放行给下一个过滤器
            return;
        }

        // 2. 提取 JWT
        jwt = authHeader.substring(7);

        try {
            // 3. 从 JWT 中提取用户名
            username = jwtUtil.extractUsername(jwt);
        } catch (ExpiredJwtException e) {
            // 如果 token 过期，直接设置 401 响应并返回
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.getWriter().write("Token has expired");
            return;
        }

        // 4. 校验 JWT
        // SecurityContextHolder.getContext().getAuthentication() == null 确保了此过滤器只执行一次
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // 验证 token 是否有效
            // 注意：在真实的微服务场景中，我们可能不需要再次查询数据库来验证用户
            // 只要 token 能被正确解析且未过期，我们就信任它。
            // 这里我们简化处理，直接使用 username 进行验证。
            if (jwtUtil.validateToken(jwt, username)) {
                // 7. 创建认证令牌
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        username, // principal: 通常是用户名或用户对象
                        null,     // credentials: 密码，因为已经通过 JWT 认证，所以设为 null
                        null      // authorities: 用户权限列表，我们暂时不实现
                );
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // 8. 更新 SecurityContextHolder
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        // 9. 放行请求
        filterChain.doFilter(request, response);
    }
}
