package com.atra.orderservice.controller;

import com.atra.orderservice.domain.entity.Order;
import com.atra.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.atra.orderservice.util.JwtUtil;


import java.util.List;

/**
 * 订单控制器。
 * <p>
 * 负责处理与订单相关的 HTTP 请求。
 * </p>
 */
@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
    private final JwtUtil jwtUtil;

    /**
     * 通过构造函数注入 OrderService 和 JwtUtil。
     *
     * @param orderService 订单服务。
     * @param jwtUtil      JWT 工具类。
     */
    @Autowired
    public OrderController(OrderService orderService, JwtUtil jwtUtil) {
        this.orderService = orderService;
        this.jwtUtil = jwtUtil;
    }

    /**
     * 获取当前登录用户的所有订单。
     *
     * @param token Authorization 请求头中的 JWT。
     * @return 包含订单列表的 ResponseEntity。
     */
    @GetMapping
    public ResponseEntity<List<Order>> getUserOrders(@RequestHeader("Authorization") String token) {
        // 从 token 中提取 "Bearer " 前缀
        String jwt = token.substring(7);
        // 从 JWT 中解析出用户 ID
        Long currentUserId = jwtUtil.extractUserId(jwt);
        // 根据用户 ID 查询订单
        List<Order> orders = orderService.getOrdersByUserId(currentUserId);
        return ResponseEntity.ok(orders);
    }
}
