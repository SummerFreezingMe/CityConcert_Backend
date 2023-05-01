package com.cityconcert.controller;

import com.cityconcert.domain.dto.VenueDTO;
import com.cityconcert.service.impl.VenueServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/venue")
public class VenueController {
    private final VenueServiceImpl venueService;
    public VenueController(VenueServiceImpl vsi) {
        this.venueService = vsi;
    }
    @GetMapping(value = "/get/{id}", produces = {"application/json", "application/xml"})
    public VenueDTO getVenue(@PathVariable Long id) {
        return venueService.findOne(id).get();
    }

    @GetMapping(value = "/get_all", produces = {"application/json", "application/xml"})
    public List<VenueDTO> getVenues() {
        return venueService.findAll();
    }
}
