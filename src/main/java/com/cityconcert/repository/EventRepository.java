package com.cityconcert.repository;

import com.cityconcert.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Spring Data JPA repository for the Event entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    @Query(value = "select * from event where id in " +
            "(select event_id from ticket where price > ?1 and price < ?2)",nativeQuery = true)
    List<Event> findEventByTicketPrice(Double priceLowest, Double priceHighest);
    @Query(value = "select * from event  where start_time > ?1 and start_time < ?2",nativeQuery = true)
    List<Event> findEventByDate(LocalDateTime dateFirst, LocalDateTime dateLast);

    Event findEventById(Long id);
}
