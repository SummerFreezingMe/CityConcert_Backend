package com.cityconcert.controller;

import com.cityconcert.domain.dto.EventDTO;
import com.cityconcert.service.impl.EventServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "event")
public class EventController {
    private final EventServiceImpl esi;

    public EventController(EventServiceImpl esi) {
        this.esi = esi;
    }

    @PostMapping(value = "/add", produces = {"application/json", "application/xml"})
    public EventDTO addEvent(@RequestBody EventDTO event) {
        return esi.save(event);
    }

    @PostMapping(value = "/update", produces = {"application/json", "application/xml"})
    public EventDTO updateEvent(@RequestBody EventDTO event) {
        return esi.update(event);
    }

    @GetMapping(value = "/get/{id}", produces = {"application/json", "application/xml"})
    public EventDTO getEvent(@PathVariable Long id) {
        return esi.findOne(id).get();
    }

    @DeleteMapping(value = "/delete/{id}",
            produces = {"application/json", "application/xml"})
    public void deleteEvent(@PathVariable Long id) {
        esi.delete(id);
    }

    @GetMapping(value = "/get_all", produces = {"application/json", "application/xml"})
    public List<EventDTO> displayAllEvents() {
        return esi.findAll();
    }


    @GetMapping(value = "/filter_by_genre/{descriptor}", produces = {"application/json", "application/xml"})
    public List<EventDTO> filterEventsByGenre(@PathVariable String descriptor) {
        return esi.findByDescriptor(descriptor);
    }
    @GetMapping(value = "/filter_by_date/{date}", produces = {"application/json", "application/xml"})
    public List<EventDTO> filterEventsByDate(@PathVariable String date) {
        return esi.findByDate(date);
    }
    @GetMapping(value = "/filter_by_name/{name}", produces = {"application/json", "application/xml"})
    public List<EventDTO> filterEventsByName(@PathVariable String name) {
        return esi.findByName(name);
    }
}
