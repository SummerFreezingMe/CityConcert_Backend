package com.cityconcert.service.impl;

import com.cityconcert.domain.Ticket;
import com.cityconcert.domain.dto.RequestDTO;
import com.cityconcert.domain.enumeration.TicketStatus;
import com.cityconcert.repository.TicketRepository;
import com.cityconcert.service.TicketService;
import com.cityconcert.domain.dto.TicketDTO;
import com.cityconcert.mapper.TicketMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Ticket}.
 */
@Service
@Transactional
public class TicketServiceImpl implements TicketService {

    private final Logger log = LoggerFactory.getLogger(TicketServiceImpl.class);

    private final TicketRepository ticketRepository;

    private final TicketMapper ticketMapper;

    private final UserServiceImpl userService;

    public TicketServiceImpl(TicketRepository ticketRepository, TicketMapper ticketMapper, UserServiceImpl userService) {
        this.ticketRepository = ticketRepository;
        this.ticketMapper = ticketMapper;
        this.userService = userService;
    }

    @Override
    public TicketDTO save(TicketDTO ticketDTO) {
        log.debug("Request to save Ticket : {}", ticketDTO);
        Ticket ticket = ticketMapper.toEntity(ticketDTO);
        ticket = ticketRepository.save(ticket);
        return ticketMapper.toDto(ticket);
    }

    @Override
    public TicketDTO update(TicketDTO ticketDTO) {
        log.debug("Request to update Ticket : {}", ticketDTO);
        Ticket ticket = ticketMapper.toEntity(ticketDTO);
        ticket = ticketRepository.save(ticket);
        return ticketMapper.toDto(ticket);
    }

    @Override
    public Optional<TicketDTO> partialUpdate(TicketDTO ticketDTO) {
        log.debug("Request to partially update Ticket : {}", ticketDTO);

        return ticketRepository
            .findById(ticketDTO.getId())
            .map(existingTicket -> {
                ticketMapper.partialUpdate(existingTicket, ticketDTO);

                return existingTicket;
            })
            .map(ticketRepository::save)
            .map(ticketMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TicketDTO> findAll() {
        log.debug("Request to get all Tickets");
        return ticketRepository.findAll().stream().map(ticketMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TicketDTO> findOne(Long id) {
        log.debug("Request to get Ticket : {}", id);
        return ticketRepository.findById(id).map(ticketMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Ticket : {}", id);
        ticketRepository.deleteById(id);
    }

    @Override
    public List<TicketDTO> ticketsByUser(Long userId) {
        return ticketRepository.findByUserId(userId).stream().map(ticketMapper::toDto)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    public TicketDTO exchangeTickets(RequestDTO exchangeRequest) {
        Long currentUserId = userService.getCurrentUser().getId();
        Ticket ticketFromRequestAuthor = ticketRepository.findBySeatAndUserId(
                exchangeRequest.getCurrentSeat(),exchangeRequest.getUserId()
        ).orElse(null);
        Ticket ticketFromCurrentUser = ticketRepository.findBySeatAndUserId(
                exchangeRequest.getSeatFromUser(), currentUserId
        ).orElse(null);
        if (exchangeRequest.getWantedSeat().contains(exchangeRequest.getSeatFromUser()) &&
                (ticketFromCurrentUser != null) && (ticketFromRequestAuthor != null)) {
ticketFromCurrentUser.setUserId(exchangeRequest.getUserId());
ticketFromRequestAuthor.setUserId(currentUserId);
        }
        return ticketMapper.toDto(ticketFromCurrentUser);
    }

    public TicketDTO buyTicket(Long id) {
        Ticket boughtTicket = ticketRepository.findById(id).orElse(null);
        if (boughtTicket != null) {
            boughtTicket.setStatus(TicketStatus.SOLD);

        if (userService.getCurrentUser()!=null) {
            boughtTicket.setUserId(userService.getCurrentUser().getId());
        }else
            boughtTicket.setUserId(0L);
        ticketRepository.save(boughtTicket); }
        return ticketMapper.toDto(boughtTicket);
    }
}
