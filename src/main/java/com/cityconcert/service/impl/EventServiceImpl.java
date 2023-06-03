package com.cityconcert.service.impl;

import com.cityconcert.domain.dto.EventDTO;
import com.cityconcert.domain.dto.UserDTO;
import com.cityconcert.domain.enumeration.TicketStatus;
import com.cityconcert.domain.model.Event;
import com.cityconcert.domain.model.Ticket;
import com.cityconcert.mapper.EventMapper;
import com.cityconcert.repository.EventRepository;
import com.cityconcert.repository.TicketRepository;
import com.cityconcert.service.EventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Event}.
 */
@Service
@Transactional
public class EventServiceImpl implements EventService {

    private final Logger log = LoggerFactory.getLogger(EventServiceImpl.class);

    private final EventRepository eventRepository;

    private final TicketRepository ticketRepository;

    private final EventMapper eventMapper;

    private final UserServiceImpl userService;

    public EventServiceImpl(EventRepository eventRepository,
                            TicketRepository ticketRepository,
                            EventMapper eventMapper,
                            UserServiceImpl userService) {
        this.eventRepository = eventRepository;
        this.ticketRepository = ticketRepository;
        this.eventMapper = eventMapper;
        this.userService = userService;
    }

    @Override
    public EventDTO save(EventDTO eventDTO) {
        log.debug("Request to save Event : {}", eventDTO);
        Event event = eventMapper.toEntity(eventDTO);
        event = eventRepository.save(event);
        generateTickets(event);
        return eventMapper.toDto(event);
    }

    private void generateTickets(Event eventDTO) {
        List<String> ticketsAmount = new ArrayList<>(Arrays.asList(eventDTO.getTicketLimit().split(", ")));
        List<String> ticketsPrices = new ArrayList<>(Arrays.asList(eventDTO.getTicketLimit().split(", ")));
        long idCounter = eventRepository.findAll().size() * 1000L;
        for (int j = 0; j < ticketsAmount.size(); j++) {
            for (int i = 1; i < Integer.parseInt(ticketsAmount.get(j)) + 1; i++) {
                ticketRepository.save(new Ticket(idCounter, Double.parseDouble(ticketsPrices.get(j)),
                        "" + ((char) (65 + j)) + i, TicketStatus.AVAILABLE,
                        null, null, eventDTO.getId()));
                idCounter++;
            }
        }
    }

    @Override
    public EventDTO update(EventDTO eventDTO) {
        log.debug("Request to update Event : {}", eventDTO);
        Event event = eventMapper.toEntity(eventDTO);
        event = eventRepository.save(event);
        return eventMapper.toDto(event);
    }

    @Override
    public Optional<EventDTO> partialUpdate(EventDTO eventDTO) {
        log.debug("Request to partially update Event : {}", eventDTO);

        return eventRepository
                .findById(eventDTO.getId())
                .map(existingEvent -> {
                    eventMapper.partialUpdate(existingEvent, eventDTO);

                    return existingEvent;
                })
                .map(eventRepository::save)
                .map(eventMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EventDTO> findAll() {
        log.debug("Request to get all Events");
        return eventRepository.findAll().stream().map(eventMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<EventDTO> findOne(Long id) {
        log.debug("Request to get Event : {}", id);
        return eventRepository.findById(id).map(eventMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Event : {}", id);
        eventRepository.deleteById(id);
    }

    @Override
    public List<EventDTO> findByDescriptor(List<String> descriptors) {
        List<Event> allEvents = eventRepository.findAll();
        List<Event> selectedEvents = new ArrayList<>();
        for (String descriptor :
                descriptors) {
            allEvents.removeIf(e -> !e.getGenreDescriptors().toLowerCase().contains(descriptor.toLowerCase()));
            List<Event> eventsByDescriptor = allEvents.stream().filter(e ->
                            e.getGenreDescriptors().toLowerCase().contains(descriptor.toLowerCase())
                                    && !selectedEvents.contains(e))
                    .collect(Collectors.toList());
            selectedEvents.addAll(eventsByDescriptor);
        }
        return allEvents.stream().map(eventMapper::toDto)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    public List<EventDTO> findByDate(LocalDateTime dateFirst, LocalDateTime dateLast) {
        List<Event> eventsByDate =
                eventRepository.findEventByDate(dateFirst, dateLast);
        return eventsByDate.stream().map(eventMapper::toDto)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    public List<EventDTO> findByPrice(Double priceLowest, Double priceHighest) {
        List<Event> eventByPrice =
                eventRepository.findEventByTicketPrice(priceLowest, priceHighest);
        return eventByPrice.stream().map(eventMapper::toDto)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    public List<EventDTO> findByName(String name) {
        List<Event> allEvents = eventRepository.findAll();
        allEvents.removeIf(e -> !e.getName().toLowerCase().contains(name.toLowerCase()));
        return allEvents.stream().map(eventMapper::toDto)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    public List<EventDTO> findByFilters(Map<String, Object> filters) {
        List<EventDTO> eventsByGenre = findByDescriptor((List<String>) filters.get("genre"));
        List<EventDTO> eventsByDate = findByDate((LocalDateTime) filters.get("date_first"), (LocalDateTime) filters.get("date_last"));
        List<EventDTO> eventsByPrice = findByPrice((Double) filters.get("price_lowest"), (Double) filters.get("price_highest"));
        eventsByGenre.retainAll(eventsByPrice);
        eventsByGenre.retainAll(eventsByDate);
        return eventsByGenre;
    }

    @Override
    public List<EventDTO> fetchRecommendations() {
        UserDTO currUser = userService.getCurrentUser();
        if (currUser == null || ticketRepository.findByUserId(currUser.getId()).size() == 0) {
            return eventRepository.findAll().stream().map(eventMapper::toDto)
                    .collect(Collectors.toList());
        } else {
            List<String> genreDescriptors = new ArrayList<>();
            List<Ticket> userTickets = ticketRepository.findByUserId(currUser.getId());
            for (Ticket ticket : userTickets) {
                Event e = eventRepository.findEventById(ticket.getEventId());
                genreDescriptors.addAll(new ArrayList<>(Arrays.asList(e.getGenreDescriptors().split(" , "))));
            }
            return findByDescriptor(genreDescriptors);
        }
    }

}
