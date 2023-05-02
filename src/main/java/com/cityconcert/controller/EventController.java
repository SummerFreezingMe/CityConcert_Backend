package com.cityconcert.controller;

import com.cityconcert.domain.dto.EventDTO;
import com.cityconcert.service.impl.EventServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "event")
public class EventController {
    private final EventServiceImpl esi;

    public EventController(EventServiceImpl esi) {
        this.esi = esi;
    }

    @GetMapping(value = "/get/{id}", produces = {"application/json", "application/xml"})
    public EventDTO getEvent(@PathVariable Long id) {
        return esi.findOne(id).orElse(null);
    }


    @GetMapping(value = "/get_all", produces = {"application/json", "application/xml"})
    public List<EventDTO> displayAllEvents() {
        return esi.findAll();
    }


    @PostMapping(value = "/filter_by_genre/{descriptor}", produces = {"application/json", "application/xml"})
    public List<EventDTO> filterEventsByGenre(@PathVariable String descriptor) {
        return esi.findByDescriptor(descriptor);
    }
    @PostMapping(value = "/filter_by_date", produces = {"application/json", "application/xml"})
    public List<EventDTO> filterEventsByDate(@RequestBody String startDate, @RequestBody String endDate) {
        return esi.findByDate(LocalDateTime.parse(startDate), LocalDateTime.parse(endDate));
    }
    @GetMapping(value = "/filter_by_name/{name}", produces = {"application/json", "application/xml"})
    public List<EventDTO> filterEventsByName(@PathVariable String name) {
        return esi.findByName(name);
    }

    @PostMapping(value = "/filter_by_price", produces = {"application/json", "application/xml"})
    public List<EventDTO> filterEventsByPrice(@RequestBody Double priceLowest, @RequestBody Double priceHighest) {
        return esi.findByPrice(priceLowest, priceHighest);
    }
    @PostMapping(value = "/filter", produces = {"application/json", "application/xml"})
    public List<EventDTO> filterEventsByPrice(@RequestBody Map<String,Object> filters) {
        return esi.findByFilters(filters);
    }
}
