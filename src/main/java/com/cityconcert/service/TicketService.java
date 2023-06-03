package com.cityconcert.service;

import com.cityconcert.domain.dto.RequestDTO;
import com.cityconcert.domain.dto.TicketDTO;
import com.cityconcert.domain.model.Ticket;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Ticket}.
 */
public interface TicketService {
    /**
     * Save a ticket.
     *
     * @param ticketDTO the entity to save.
     * @return the persisted entity.
     */
    TicketDTO save(TicketDTO ticketDTO);

    /**
     * Updates a ticket.
     *
     * @param ticketDTO the entity to update.
     * @return the persisted entity.
     */
    TicketDTO update(TicketDTO ticketDTO);

    /**
     * Partially updates a ticket.
     *
     * @param ticketDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<TicketDTO> partialUpdate(TicketDTO ticketDTO);

    /**
     * Get all the tickets.
     *
     * @return the list of entities.
     */
    List<TicketDTO> findAll();

    /**
     * Get the "id" ticket.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TicketDTO> findOne(Long id);

    /**
     * Delete the "id" ticket.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    List<TicketDTO> ticketsByUser(Long userId);

    List<TicketDTO> ticketsByEvent(Long eventId);

    TicketDTO exchangeTickets(RequestDTO exchangeRequest);

    TicketDTO mailTicket(TicketDTO ticket);
}
