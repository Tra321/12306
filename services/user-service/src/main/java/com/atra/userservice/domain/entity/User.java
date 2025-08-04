package com.atra.userservice.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * 用户实体类，映射到数据库的 t_user 表
 *
 * @author Cline
 */
@Data // Lombok 注解，自动生成 getter, setter, toString, equals, hashCode 方法
@Entity // 标记这是一个 JPA 实体类
@Table(name = "t_user") // 指定实体类映射的数据库表名
public class User {

    /**
     * 主键 ID，自增
     */
    @Id // 标记这是主键
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 主键生成策略为自增
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 国家/地区
     */
    private String region;

    /**
     * 证件类型
     */
    private Integer idType;

    /**
     * 证件号
     */
    private String idCard;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 固定电话
     */
    private String telephone;

    /**
     * 邮箱
     */
    private String mail;

    /**
     * 旅客类型
     */
    private Integer userType;

    /**
     * 审核状态
     */
    private Integer verifyStatus;

    /**
     * 邮编
     */
    private String postCode;

    /**
     * 地址
     */
    private String address;

    /**
     * 注销时间戳
     */
    private Long deletionTime;
}
