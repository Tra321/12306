package com.atra.ticketservice.service;

import com.atra.ticketservice.domain.entity.Train;

import java.util.List;

/**
 * 车票服务接口
 *
 * @author Cline
 */
public interface TicketService {

    /**
     * 根据出发地和目的地查询车票列表
     *
     * @param fromStation 出发地
     * @param toStation   目的地
     * @return 车票列表
     */
    List<Train> queryTickets(String fromStation, String toStation);
}
