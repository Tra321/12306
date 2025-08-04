package com.atra.ticketservice.dao;

import com.atra.ticketservice.domain.entity.Train;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 列车 Repository
 *
 * @author Cline
 */
public interface TrainRepository extends JpaRepository<Train, Long> {

    /**
     * 根据始发站和终点站查询列车列表
     *
     * @param startStation 始发站
     * @param endStation   终点站
     * @return 列车列表
     */
    List<Train> findByStartStationAndEndStation(String startStation, String endStation);
}
