package com.atra.orderservice.service.impl;

import com.atra.orderservice.dao.OrderRepository;
import com.atra.orderservice.domain.entity.Order;
import com.atra.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 订单服务的实现类。
 * <p>
 * 实现了 OrderService 接口中定义的业务方法。
 * </p>
 */
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    /**
     * 通过构造函数注入 OrderRepository。
     *
     * @param orderRepository 订单数据访问对象。
     */
    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    /**
     * 根据用户 ID 获取该用户的所有订单。
     *
     * @param userId 用户的 ID。
     * @return 该用户的订单列表。
     */
    @Override
    public List<Order> getOrdersByUserId(Long userId) {
        // 调用 repository 的方法来查询数据库
        return orderRepository.findByUserId(userId);
    }
}
