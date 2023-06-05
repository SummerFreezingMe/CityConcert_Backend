package com.cityconcert.service.impl;

import com.cityconcert.domain.dto.EventDTO;
import com.cityconcert.domain.dto.RequestDTO;
import com.cityconcert.domain.dto.TicketDTO;
import com.cityconcert.domain.enumeration.TicketStatus;
import com.cityconcert.domain.model.Ticket;
import com.cityconcert.mapper.TicketMapper;
import com.cityconcert.repository.TicketRepository;
import com.cityconcert.service.TicketService;
import com.cityconcert.service.exceptions.TicketNotFoundException;
import com.cityconcert.service.mail.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    private final EventServiceImpl eventService;

    private final MailService mailService;

    public TicketServiceImpl(TicketRepository ticketRepository, TicketMapper ticketMapper, UserServiceImpl userService, EventServiceImpl eventService, MailService mailService) {
        this.ticketRepository = ticketRepository;
        this.ticketMapper = ticketMapper;
        this.userService = userService;
        this.eventService = eventService;
        this.mailService = mailService;
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
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public List<TicketDTO> ticketsByEvent(Long eventId) {
        return ticketRepository.findByEventId(eventId).stream().map(ticketMapper::toDto)
                .collect(Collectors.toCollection(ArrayList::new));
    }



    @Override
    public TicketDTO exchangeTickets(RequestDTO exchangeRequest, Long currentUserId) {
        Ticket ticketFromRequestAuthor = ticketRepository.findBySeatAndUserId(
                exchangeRequest.getCurrentSeat(), exchangeRequest.getUserId()
        ).orElseThrow(TicketNotFoundException::new);
        Ticket ticketFromCurrentUser = ticketRepository.findBySeatAndUserId(
                exchangeRequest.getSeatFromUser(), currentUserId
        ).orElseThrow(TicketNotFoundException::new);
        if (exchangeRequest.getWantedSeat().contains(exchangeRequest.getSeatFromUser())) {
            ticketFromCurrentUser.setUserId(exchangeRequest.getUserId());
            ticketFromRequestAuthor.setUserId(currentUserId);
        }
        return ticketMapper.toDto(ticketFromCurrentUser);
    }

    @Override
    public TicketDTO mailTicket(TicketDTO ticket, String email) {
        EventDTO event = eventService.findOne(ticket.getEventId()).orElseThrow(TicketNotFoundException::new);
        String mailMessage;
        mailMessage = "Here is your ticket: " + ticket.getSeat() + " на мероприятие " + event.getName();
        String finalMailMessage = mailMessage;
        userService.findOne(ticket.getUserId()).ifPresent(
                ticketOwner -> mailService.sendEmail(ticketOwner.getEmail(),
                        "Ticket", finalMailMessage, false, false));
        return ticket;
    }
    @Override
    public TicketDTO buyTicket(TicketDTO ticket) {
        Ticket boughtTicket = ticketRepository.findById(ticket.getId()).
                orElseThrow(TicketNotFoundException::new);
        boughtTicket.setStatus(TicketStatus.SOLD);
        boughtTicket.setPurchaseDate(LocalDateTime.now());
        ticketRepository.save(boughtTicket);
        return ticketMapper.toDto(boughtTicket);
    }
}
