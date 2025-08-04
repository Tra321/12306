package com.atra.orderservice.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 订单实体类，映射到数据库中的 t_order 表。
 */
@Data
@Entity
@Table(name = "t_order")
public class Order {

    /**
     * 订单的唯一标识符（主键）。
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 订单号，具有业务意义的唯一标识。
     */
    private String orderId;

    /**
     * 创建该订单的用户的 ID。
     */
    private Long userId;

    /**
     * 订单关联的车次号。
     */
    private String trainNumber;

    /**
     * 出发站。
     */
    private String departure;

    /**
     * 到达站。
     */
    private String destination;

    /**
     * 火车出发时间。
     */
    private LocalDateTime departureTime;

    /**
     * 乘客姓名。
     */
    private String passengerName;

    /**
     * 座位信息，例如“一等座 01车 01A”。
     */
    private String seatInfo;

    /**
     * 订单金额。
     */
    private BigDecimal price;

    /**
     * 订单状态（例如：待支付、已支付、已取消）。
     */
    private String status;

    /**
     * 订单创建时间。
     */
    private LocalDateTime createTime;

    /**
     * 订单最后更新时间。
     */
    private LocalDateTime updateTime;

    /**
     * 删除标识，0表示未删除，1表示已删除。
     */
    private Integer delFlag;

    /**
     * 注销时间戳。
     */
    private Long deletionTime;
}
