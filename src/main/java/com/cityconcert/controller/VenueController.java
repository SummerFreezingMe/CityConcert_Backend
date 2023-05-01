package com.cityconcert.controller;

import com.cityconcert.domain.dto.VenueDTO;
import com.cityconcert.service.impl.VenueServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "venue")
public class VenueController {
    private final VenueServiceImpl vsi;
    public VenueController(VenueServiceImpl vsi) {
        this.vsi = vsi;
    }
    @GetMapping(value = "/get/{id}", produces = {"application/json", "application/xml"})
    public VenueDTO getVenue(@PathVariable Long id) {
        return vsi.findOne(id).get();
    }
}
