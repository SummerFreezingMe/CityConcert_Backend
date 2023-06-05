package com.cityconcert.controller;

import com.cityconcert.domain.dto.RequestDTO;
import com.cityconcert.domain.dto.TicketDTO;
import com.cityconcert.service.exceptions.TicketNotFoundException;
import com.cityconcert.service.impl.TicketServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Билеты", description = "Методы, взаимодействующие с билетами")
@RequestMapping(value = "/ticket")
public class TicketController {
    private final TicketServiceImpl tsi;

    public TicketController(TicketServiceImpl tsi) {
        this.tsi = tsi;
    }

    @PostMapping(value = "/add", produces = {"application/json", "application/xml"})
    @Operation(summary = "Добавляем экземпляр билета")
    public TicketDTO addTicket(@RequestBody TicketDTO ticket) {
        return tsi.save(ticket);
    }

    @GetMapping(value = "/get/{id}", produces = {"application/json", "application/xml"})
    @Operation(summary = "Получаем экземпляр билета по его Id")
    public TicketDTO getTicket(@PathVariable Long id) {
        return tsi.findOne(id).orElseThrow(TicketNotFoundException::new);
    }

    @DeleteMapping(value = "/delete/{id}",
            produces = {"application/json", "application/xml"})
    @Operation(summary = "Удаляем экземпляр билета по его Id")
    public void deleteTicket(@PathVariable Long id) {
        tsi.delete(id);
    }

    @PostMapping(value = "/buy/{id}", produces = {"application/json", "application/xml"})
    @Operation(summary = "Процедура покупки билета")
    public TicketDTO buyTicket(@PathVariable Long id,@RequestBody TicketDTO ticket) {
        return tsi.buyTicket(id,ticket);
    }

    @GetMapping(value = "/user/{id}", produces = {"application/json", "application/xml"})
    @Operation(summary = "Отображение билетов пользователя по его Id")
    public List<TicketDTO> TicketsByUser(@PathVariable Long id) {
        return tsi.ticketsByUser(id);
    }

    @GetMapping(value = "/event/{eventId}", produces = {"application/json", "application/xml"})
    @Operation(summary = "Отображение билетов мероприятия по его Id")
    public List<TicketDTO> TicketsByEvent(@PathVariable Long eventId) {
        return tsi.ticketsByEvent(eventId);
    }

    @PostMapping(value = "/exchange/{id}", produces = {"application/json", "application/xml"})
    @Operation(summary = "Обмен билетов между пользователями")
    public TicketDTO exchangeTickets(@RequestBody RequestDTO exchangeRequest,@PathVariable Long id) {
        return tsi.exchangeTickets(exchangeRequest,id);
    }

    @PostMapping(value = "/mail/{email}", produces = {"application/json", "application/xml"})
    @Operation(summary = "Отправка билета по почте")
    public TicketDTO mailTicket(@RequestBody TicketDTO ticket,@PathVariable String email) {
        return tsi.mailTicket(ticket,email);
    }
}

