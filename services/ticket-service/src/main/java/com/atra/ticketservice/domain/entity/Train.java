package com.atra.ticketservice.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.sql.Time;

/**
 * 列车实体
 *
 * @author Cline
 */
@Data
@Entity
@Table(name = "t_train")
public class Train {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 车次
     */
    private String trainNumber;

    /**
     * 列车类型 0:高铁 1:动车 2:普通
     */
    private Integer trainType;

    /**
     * 始发站
     */
    private String startStation;

    /**
     * 终点站
     */
    private String endStation;

    /**
     * 出发时间
     */
    private Time departureTime;

    /**
     * 到达时间
     */
    private Time arrivalTime;
}
