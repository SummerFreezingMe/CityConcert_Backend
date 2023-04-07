package com.cityconcert.controller;

import com.cityconcert.domain.dto.TicketDTO;
import com.cityconcert.service.impl.TicketServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/ticket")
public class TicketController {
    private final TicketServiceImpl tsi;

    public TicketController(TicketServiceImpl tsi) {
        this.tsi = tsi;
    }
    @PostMapping(value = "/add", produces = {"application/json", "application/xml"})
    public TicketDTO addTicket(@RequestBody TicketDTO ticket) {
        return tsi.save(ticket);
    }

    @GetMapping(value = "/ge/{id}", produces = {"application/json", "application/xml"})
    public TicketDTO getTicket(@PathVariable Long id) {
        return tsi.findOne(id).get();
    }

    @DeleteMapping(value = "/delete/{id}",
            produces = {"application/json", "application/xml"})
    public void deleteTicket(@PathVariable Long id) {
        tsi.delete(id);
    }

    @GetMapping(value = "/buy/{id}", produces = {"application/json", "application/xml"})
    public TicketDTO buyTicket(@PathVariable Long id) {
        return tsi.buyTicket(id);
    }

    @GetMapping(value = "/user/{userId}", produces = {"application/json", "application/xml"})
    public List<TicketDTO> TicketsByUser(@PathVariable Long userId) {
        return tsi.ticketsByUser(userId);
    }

}
