package com.atra.userservice.config;

import com.atra.userservice.dao.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.atra.userservice.filter.JwtAuthenticationFilter;

/**
 * Spring Security 配置类
 *
 * @author Cline
 */
@Configuration // 标记这是一个 Spring 配置类
@EnableWebSecurity // 启用 Spring Security 的 Web 安全支持
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserRepository userRepository;
    private final JwtAuthenticationFilter jwtAuthFilter;

    /**
     * 将 PasswordEncoder 注册为 Spring Bean
     * 我们使用 BCryptPasswordEncoder，这是一个强度很高的哈希算法，并且自带盐值。
     *
     * @return PasswordEncoder 的实例
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 配置安全过滤器链
     *
     * @param http HttpSecurity 对象，用于配置安全规则
     * @return SecurityFilterChain 的实例
     * @throws Exception 配置过程中可能抛出的异常
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 禁用 CSRF 保护
                .csrf(csrf -> csrf.disable())
                // 配置请求授权规则
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/user-service/register", "/api/user-service/login").permitAll()
                        .anyRequest().authenticated()
                )
                // 添加我们的自定义 JWT 过滤器
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    /**
     * 提供 UserDetailsService Bean
     * Spring Security 会使用这个 Bean 来加载用户信息
     *
     * @return UserDetailsService 的实现
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> userRepository.findByUsername(username)
                .map(user -> new org.springframework.security.core.userdetails.User(
                        user.getUsername(),
                        user.getPassword(),
                        // 我们暂时不实现角色权限，所以这里给一个空列表
                        java.util.Collections.emptyList()
                ))
                .orElseThrow(() -> new UsernameNotFoundException("用户 " + username + " 未找到"));
    }

    /**
     * 提供 AuthenticationProvider Bean
     * 这是 Spring Security 的认证提供者，它会使用 UserDetailsService 和 PasswordEncoder
     *
     * @return AuthenticationProvider 的实例
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    /**
     * 提供 AuthenticationManager Bean
     *
     * @param config AuthenticationConfiguration
     * @return AuthenticationManager 的实例
     * @throws Exception
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
