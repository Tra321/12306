package com.atra.ticketservice.controller;

import com.atra.ticketservice.domain.entity.Train;
import com.atra.ticketservice.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 车票服务控制器
 *
 * @author Cline
 */
@RestController
@RequestMapping("/api/ticket-service")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    /**
     * 车票查询接口
     *
     * @param fromStation 出发地
     * @param toStation   目的地
     * @return 车票列表
     */
    @GetMapping("/query")
    public List<Train> queryTickets(
            @RequestParam("from") String fromStation,
            @RequestParam("to") String toStation) {
        return ticketService.queryTickets(fromStation, toStation);
    }
}
