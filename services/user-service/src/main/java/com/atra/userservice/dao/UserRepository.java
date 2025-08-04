package com.atra.userservice.dao;

import com.atra.userservice.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * 用户表的数据库访问层 (DAO)
 * JpaRepository 提供了完整的 CRUD 功能
 *
 * @author Cline
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * 根据用户名查找用户
     * Spring Data JPA 会根据方法名自动生成查询
     *
     * @param username 用户名
     * @return 一个包含 User 的 Optional，如果找不到则为空
     */
    Optional<User> findByUsername(String username);
}
