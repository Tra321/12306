package com.atra.userservice.dto;

import lombok.Data;

/**
 * 用户信息数据传输对象 (DTO)，用于向上层或前端返回安全的用户数据
 *
 * @author Cline
 */
@Data
public class UserDTO {

    private Long id;
    private String username;
    private String realName;
    private String phone;
    private String mail;
    private Integer userType;
    private Integer verifyStatus;
}
