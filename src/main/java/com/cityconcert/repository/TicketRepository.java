package com.cityconcert.repository;

import com.cityconcert.domain.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data JPA repository for the Ticket entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByUserId(Long userId);

    Optional<Ticket> findBySeatAndUserId(String currentSeat, Long userId);

    List<Ticket> findByEventId(Long eventId);
}
