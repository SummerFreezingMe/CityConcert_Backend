package com.cityconcert.service;

import com.cityconcert.domain.dto.EventDTO;
import com.cityconcert.domain.model.Event;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Service Interface for managing {@link Event}.
 */
public interface EventService {
    /**
     * Save an event.
     *
     * @param eventDTO the entity to save.
     * @return the persisted entity.
     */
    EventDTO save(EventDTO eventDTO);

    /**
     * Updates a event.
     *
     * @param eventDTO the entity to update.
     * @return the persisted entity.
     */
    EventDTO update(EventDTO eventDTO);

    /**
     * Partially updates an event.
     *
     * @param eventDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<EventDTO> partialUpdate(EventDTO eventDTO);

    /**
     * Get all the events.
     *
     * @return the list of entities.
     */
    List<EventDTO> findAll();

    /**
     * Get the "id" event.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EventDTO> findOne(Long id);

    /**
     * Delete the "id" event.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    List<EventDTO> findByDescriptor(List<String> descriptors);


    List<EventDTO> findByDate(LocalDateTime dateFirst, LocalDateTime dateLast);

    List<EventDTO> findByPrice(Double priceLowest, Double priceHighest);

    List<EventDTO> findByName(String name);

    List<EventDTO> findByFilters(Map<String, Object> filters);

    List<EventDTO> fetchRecommendations();
}
