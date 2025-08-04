package com.atra.ticketservice.dao;

import com.atra.ticketservice.domain.entity.Station;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 车站 Repository
 *
 * @author Cline
 */
public interface StationRepository extends JpaRepository<Station, Long> {
}
