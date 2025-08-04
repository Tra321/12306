package com.atra.userservice.dto;

import lombok.Data;

/**
 * 用户登录数据传输对象 (DTO)
 *
 * @author Cline
 */
@Data // Lombok 注解，自动生成 getter, setter 等方法
public class UserLoginDTO {

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;
}
