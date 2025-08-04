package com.atra.userservice.service;

import com.atra.userservice.domain.entity.User;
import com.atra.userservice.dto.UserDTO;
import com.atra.userservice.dto.UserLoginDTO;

/**
 * 用户服务接口层
 *
 * @author Cline
 */
public interface UserService {

    /**
     * 用户注册
     *
     * @param user 注册用户实体
     */
    void register(User user);

    /**
     * 用户登录
     *
     * @param userLoginDTO 登录请求 DTO
     * @return JWT Token
     */
    String login(UserLoginDTO userLoginDTO);

    /**
     * 根据用户名查询用户信息
     *
     * @param username 用户名
     * @return 用户信息 DTO
     */
    UserDTO getUserInfoByUsername(String username);
}
