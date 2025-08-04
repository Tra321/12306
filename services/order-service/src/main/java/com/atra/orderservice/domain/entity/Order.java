package com.atra.orderservice.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "t_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String orderId;
    private Long userId;
    private String trainNumber;
    private String departure;
    private String destination;
    private LocalDateTime departureTime;
    private String passengerName;
    private String seatInfo;
    private BigDecimal price;
    private String status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
