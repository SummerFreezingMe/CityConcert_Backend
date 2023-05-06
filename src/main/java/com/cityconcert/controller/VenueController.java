package com.cityconcert.controller;

import com.cityconcert.domain.dto.VenueDTO;
import com.cityconcert.service.impl.VenueServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name="Площадки",description = "Методы, взаимодействующие с площадками, на которых проводятся мероприятия ")
@RequestMapping(value = "/venue")
public class VenueController {
    private final VenueServiceImpl venueService;
    public VenueController(VenueServiceImpl vsi) {
        this.venueService = vsi;
    }
    @GetMapping(value = "/get/{id}", produces = {"application/json", "application/xml"})
    @Operation(summary = "Возвращаем экземпляр площадки по его Id")
    public VenueDTO getVenue(@PathVariable Long id) {
        return venueService.findOne(id);
    }

    @GetMapping(value = "/get_all", produces = {"application/json", "application/xml"})
    @Operation(summary = "Возвращаем список всех площадок")
    public List<VenueDTO> getVenues() {
        return venueService.findAll();
    }
}
