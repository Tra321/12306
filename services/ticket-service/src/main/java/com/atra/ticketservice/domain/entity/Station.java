package com.atra.ticketservice.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * 车站实体
 *
 * @author Cline
 */
@Data
@Entity
@Table(name = "t_station")
public class Station {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 车站名称
     */
    private String name;

    /**
     * 车站编码
     */
    private String code;

    /**
     * 拼音
     */
    private String pinyin;

    /**
     * 首字母
     */
    private String initial;
}
