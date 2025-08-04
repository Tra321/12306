package com.atra.ticketservice.service.impl;

import com.atra.ticketservice.dao.TrainRepository;
import com.atra.ticketservice.domain.entity.Train;
import com.atra.ticketservice.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 车票服务实现类
 *
 * @author Cline
 */
@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TrainRepository trainRepository;

    /**
     * 根据出发地和目的地查询车票列表
     * <p>
     * 注意：这是一个简化的实现。真实的 12306 查询逻辑会复杂得多，
     * 需要考虑经停站、日期、余票、不同席别等。
     *
     * @param fromStation 出发地
     * @param toStation   目的地
     * @return 车票列表
     */
    @Override
    public List<Train> queryTickets(String fromStation, String toStation) {
        return trainRepository.findByStartStationAndEndStation(fromStation, toStation);
    }
}
