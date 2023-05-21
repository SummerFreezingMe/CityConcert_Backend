package com.cityconcert.controller;

import com.cityconcert.domain.dto.EventDTO;
import com.cityconcert.service.impl.EventServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@Tag(name="Мероприятия",description = "Методы, взаимодействующие с мероприятиями")
@RequestMapping(value = "event")
public class EventController {
    private final EventServiceImpl esi;

    public EventController(EventServiceImpl esi) {
        this.esi = esi;
    }

    @GetMapping(value = "/get/{id}", produces = {"application/json", "application/xml"})
    @Operation(summary = "Получаем экземпляр мероприятия по его Id")
    public EventDTO getEvent(@PathVariable Long id) {
        return esi.findOne(id).orElseThrow(EntityNotFoundException::new);
    }


    @GetMapping(value = "/get_all", produces = {"application/json", "application/xml"})
    @Operation(summary = "Отображаем все мероприятия")

    public Page<EventDTO> displayAllEvents(@RequestParam(value = "offset", defaultValue = "0") @Min(0) Integer offset,
                                           @RequestParam(value = "limit", defaultValue = "15") @Min(1) @Max(100) Integer limit) {
        return esi.findAll(PageRequest.of(offset, limit));
    }


    @PostMapping(value = "/filter_by_genre", produces = {"application/json", "application/xml"})
    @Operation(summary = "Получаем мероприятия по определённым жанрам")
    public List<EventDTO> filterEventsByGenre(@RequestBody List<String> descriptor) {
        return esi.findByDescriptor(descriptor);
    }
    @PostMapping(value = "/filter_by_date", produces = {"application/json", "application/xml"})
    @Operation(summary = "Получаем мероприятия по диапазону дат")
    public List<EventDTO> filterEventsByDate(@RequestBody Map<String,LocalDateTime>payload) {
        return esi.findByDate(payload.get("start_date"), payload.get("end_date"));
    }
    @GetMapping(value = "/filter_by_name/{name}", produces = {"application/json", "application/xml"})
    @Operation(summary = "Получаем мероприятия по определённому имени")
    public Page<EventDTO> filterEventsByName(@PathVariable String name,
                                             @RequestParam(value = "offset", defaultValue = "0") @Min(0) Integer offset,
                                             @RequestParam(value = "limit", defaultValue = "15") @Min(1) @Max(100) Integer limit) {
        return esi.findByName(name,
                PageRequest.of(offset, limit));
    }

    @PostMapping(value = "/filter_by_price", produces = {"application/json", "application/xml"})
    @Operation(summary = "Получаем мероприятия по ценовому диапазону")
    public List<EventDTO> filterEventsByPrice(@RequestBody Double priceLowest, @RequestBody Double priceHighest) {
        return esi.findByPrice(priceLowest, priceHighest);
    }
    @PostMapping(value = "/filter", produces = {"application/json", "application/xml"})
    @Operation(summary = "Получаем мероприятия по всем существующим фильтрам")
    public Page<EventDTO> filterEvents(@RequestBody Map<String,Object> filters,
                                       @RequestParam(value = "offset", defaultValue = "0") @Min(0) Integer offset,
                                       @RequestParam(value = "limit", defaultValue = "15") @Min(1) @Max(100) Integer limit) {
        return esi.findByFilters(filters,
                PageRequest.of(offset, limit));
    }
}
