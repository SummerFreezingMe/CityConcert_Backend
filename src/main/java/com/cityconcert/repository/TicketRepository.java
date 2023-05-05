package com.cityconcert.repository;

import com.cityconcert.domain.Ticket;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Ticket entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByUserId(Long userId);

 Optional<Ticket>  findBySeatAndUserId(String currentSeat, Long userId);
}
