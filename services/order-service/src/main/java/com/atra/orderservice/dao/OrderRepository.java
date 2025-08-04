package com.atra.orderservice.dao;

import com.atra.orderservice.domain.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 订单数据访问接口。
 * <p>
 * 继承 JpaRepository，提供对订单数据的 CRUD（创建、读取、更新、删除）操作。
 * </p>
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    /**
     * 根据用户 ID 查询该用户的所有订单。
     * <p>
     * Spring Data JPA 会根据方法名自动生成查询语句。
     * </p>
     *
     * @param userId 要查询的用户的 ID。
     * @return 包含该用户所有订单的列表。如果用户没有订单，则返回空列表。
     */
    List<Order> findByUserId(Long userId);
}
