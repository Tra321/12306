package com.atra.userservice.service.impl;

import com.atra.userservice.dao.UserRepository;
import com.atra.userservice.domain.entity.User;
import com.atra.userservice.dto.UserDTO;
import com.atra.userservice.dto.UserLoginDTO;
import com.atra.userservice.service.UserService;
import com.atra.userservice.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 用户服务接口实现层
 *
 * @author Cline
 */
@Service // 标记这是一个 Spring Service Bean
@RequiredArgsConstructor // Lombok 注解，为所有 final 字段生成一个构造函数，用于依赖注入
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    /**
     * 用户注册
     *
     * @param user 注册用户实体
     */
    @Override
    public void register(User user) {
        // 1. 检查用户名是否已存在
        userRepository.findByUsername(user.getUsername()).ifPresent(existingUser -> {
            throw new IllegalStateException("用户名 " + user.getUsername() + " 已被注册");
        });

        // 2. 对密码进行加密
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        // 3. 调用 UserRepository 的 save 方法将用户数据持久化到数据库
        userRepository.save(user);
    }

    /**
     * 用户登录
     *
     * @param userLoginDTO 登录请求 DTO
     * @return JWT Token
     */
    @Override
    public String login(UserLoginDTO userLoginDTO) {
        // 1. 根据用户名查找用户
        User user = userRepository.findByUsername(userLoginDTO.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("用户名或密码错误"));

        // 2. 校验密码
        if (!passwordEncoder.matches(userLoginDTO.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("用户名或密码错误");
        }

        // 3. 生成 JWT
        return jwtUtil.generateToken(user.getUsername(), user.getId());
    }

    /**
     * 根据用户名查询用户信息
     *
     * @param username 用户名
     * @return 用户信息 DTO
     */
    @Override
    public UserDTO getUserInfoByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("用户 " + username + " 未找到"));
        return convertToDTO(user);
    }

    /**
     * 将 User 实体转换为 UserDTO
     *
     * @param user 用户实体
     * @return 用户 DTO
     */
    private UserDTO convertToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setRealName(user.getRealName());
        userDTO.setPhone(user.getPhone());
        userDTO.setMail(user.getMail());
        userDTO.setUserType(user.getUserType());
        userDTO.setVerifyStatus(user.getVerifyStatus());
        return userDTO;
    }
}
