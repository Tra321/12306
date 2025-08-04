package com.atra.userservice.controller;

import com.atra.userservice.domain.entity.User;
import com.atra.userservice.dto.UserDTO;
import com.atra.userservice.dto.UserLoginDTO;
import com.atra.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * 用户服务控制器
 *
 * @author Cline
 */
@RestController // 标记这是一个 RESTful Controller，所有方法的返回值将自动序列化为 JSON
@RequestMapping("/api/user-service") // 为该控制器下的所有端点设置一个基础路径
@RequiredArgsConstructor // Lombok 注解，用于依赖注入
public class UserController {

    private final UserService userService;

    /**
     * 用户注册 API
     *
     * @param user 包含注册信息的 User 对象，从请求体中获取
     */
    @PostMapping("/register") // 映射 HTTP POST 请求到 /api/user-service/register
    public void register(@RequestBody User user) {
        // @RequestBody 注解告诉 Spring 从 HTTP 请求体中读取数据并反序列化为 User 对象
        userService.register(user);
    }

    /**
     * 用户登录 API
     *
     * @param userLoginDTO 包含登录信息的 DTO
     * @return JWT Token
     */
    @PostMapping("/login")
    public String login(@RequestBody UserLoginDTO userLoginDTO) {
        return userService.login(userLoginDTO);
    }

    /**
     * 获取当前登录用户信息 API
     *
     * @param principal Spring Security 提供的对象，用于获取当前认证用户的信息
     * @return 用户信息 DTO
     */
    @GetMapping("/info")
    public UserDTO getUserInfo(Principal principal) {
        // principal.getName() 返回的是我们在 JwtAuthenticationFilter 中设置的用户名
        String username = principal.getName();
        return userService.getUserInfoByUsername(username);
    }
}
