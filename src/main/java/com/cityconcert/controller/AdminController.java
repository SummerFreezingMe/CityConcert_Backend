package com.cityconcert.controller;

import com.cityconcert.domain.dto.EventDTO;
import com.cityconcert.domain.dto.UserDTO;
import com.cityconcert.domain.dto.VenueDTO;
import com.cityconcert.service.impl.EventServiceImpl;
import com.cityconcert.service.impl.UserServiceImpl;
import com.cityconcert.service.impl.VenueServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final UserServiceImpl userService;

    private final EventServiceImpl eventService;

    private final VenueServiceImpl venueService;

    public AdminController(EventServiceImpl esi, UserServiceImpl userService, VenueServiceImpl venueService) {
        this.eventService = esi;
        this.userService = userService;
        this.venueService = venueService;
    }

    @GetMapping("/users")
    public List<UserDTO> userList() {
        return userService.findAll();
    }

    @PostMapping(value = "/event/add", produces = {"application/json", "application/xml"})
    public EventDTO addEvent(@RequestBody EventDTO event) {
        return eventService.save(event);
    }

    @PostMapping(value = "/event/update", produces = {"application/json", "application/xml"})
    public EventDTO updateEvent(@RequestBody EventDTO event) {
        return eventService.update(event);
    }
    @DeleteMapping(value = "/event/delete/{id}",
            produces = {"application/json", "application/xml"})
    public void deleteEvent(@PathVariable Long id) {
        eventService.delete(id);
    }
    @PostMapping(value = "/venue/add", produces = {"application/json", "application/xml"})
    public VenueDTO addVenue(@RequestBody VenueDTO venue) {
        return venueService.save(venue);
    }


    @DeleteMapping(value = "/venue/delete/{id}",
            produces = {"application/json", "application/xml"})
    public void deleteVenue(@PathVariable Long id) {
        venueService.delete(id);
    }

    @PostMapping(value = "/venue/update", produces = {"application/json", "application/xml"})
    public VenueDTO updateVenue(@RequestBody VenueDTO venue) {
        return venueService.update(venue);
    }

}