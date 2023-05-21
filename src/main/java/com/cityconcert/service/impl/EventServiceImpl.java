package com.cityconcert.service.impl;

import com.cityconcert.domain.Event;
import com.cityconcert.domain.dto.EventDTO;
import com.cityconcert.mapper.EventMapper;
import com.cityconcert.repository.EventRepository;
import com.cityconcert.service.EventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
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

    private final EventMapper eventMapper;

    public EventServiceImpl(EventRepository eventRepository, EventMapper eventMapper) {
        this.eventRepository = eventRepository;
        this.eventMapper = eventMapper;
    }

    @Override
    public EventDTO save(EventDTO eventDTO) {
        log.debug("Request to save Event : {}", eventDTO);
        Event event = eventMapper.toEntity(eventDTO);

        event = eventRepository.save(event);
        return eventMapper.toDto(event);
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
    public Page<EventDTO> findAll(PageRequest of) {
        log.debug("Request to get all Events");
        List<EventDTO> page = eventRepository.findAll(of).stream().map(eventMapper::toDto)
                .collect(Collectors.toCollection(LinkedList::new));
        return new PageImpl<>(page);
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
        for (String descriptor:
             descriptors) {
            allEvents.removeIf(e -> !e.getGenreDescriptors().toLowerCase().contains(descriptor.toLowerCase()));
                List<Event> eventsByDescriptor= allEvents.stream().filter(e ->
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
    public Page<EventDTO> findByName(String name, PageRequest of) {

        List<Event> allEvents = eventRepository.findAll();
        allEvents.removeIf(e -> !e.getName().contains(name));
        List<EventDTO> eventsByName =allEvents.stream().map(eventMapper::toDto)
                .collect(Collectors.toCollection(LinkedList::new));
        final int start = (int)of.getOffset();
        final int end = Math.min((start + of.getPageSize()), eventsByName.size());
        return new PageImpl<>(eventsByName.subList(start, end), of, eventsByName.size());
    }

    @Override
    public Page<EventDTO> findByFilters(Map<String, Object> filters, PageRequest of) {
        List<EventDTO> eventsByGenre = findByDescriptor((List<String>) filters.get("genre"));
        List<EventDTO> eventsByDate = findByDate((LocalDateTime) filters.get("date_first"), (LocalDateTime) filters.get("date_last"));
        List<EventDTO> eventsByPrice = findByPrice((Double) filters.get("price_lowest"), (Double) filters.get("price_highest"));
        eventsByGenre.retainAll(eventsByPrice);
        eventsByGenre.retainAll(eventsByDate);
        final int start = (int)of.getOffset();
        final int end = Math.min((start + of.getPageSize()), eventsByGenre.size());
        return new PageImpl<>(eventsByGenre.subList(start, end), of, eventsByGenre.size());
    }

}
