package com.cityconcert.service.impl;

import com.cityconcert.domain.Event;
import com.cityconcert.domain.dto.EventDTO;
import com.cityconcert.mapper.EventMapper;
import com.cityconcert.repository.EventRepository;
import com.cityconcert.service.EventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
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
    public List<EventDTO> findByDescriptor(String descriptor) {
        List<Event> allEvents = eventRepository.findAll();
        allEvents.removeIf(e -> !e.getGenreDescriptors().contains(descriptor));
        return allEvents.stream().map(eventMapper::toDto)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    public List<EventDTO> findByDate(String date) {
        return null;
    }

    @Override
    public List<EventDTO> findByName(String name) {
        List<Event> allEvents = eventRepository.findAll();
        allEvents.removeIf(e -> !e.getName().contains(name));
        return allEvents.stream().map(eventMapper::toDto)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    public void updateStatus() {
    }
}
