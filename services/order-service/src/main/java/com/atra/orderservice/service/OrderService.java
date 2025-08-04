package com.atra.orderservice.service;

import com.atra.orderservice.domain.entity.Order;

import java.util.List;

/**
 * 订单服务接口。
 * <p>
 * 定义了订单相关的业务操作。
 * </p>
 */
public interface OrderService {

    /**
     * 根据用户 ID 获取该用户的所有订单。
     *
     * @param userId 用户的 ID。
     * @return 该用户的订单列表。
     */
    List<Order> getOrdersByUserId(Long userId);
}
