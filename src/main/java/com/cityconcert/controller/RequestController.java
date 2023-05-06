package com.cityconcert.controller;

import com.cityconcert.domain.dto.RequestDTO;
import com.cityconcert.service.impl.RequestServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@Tag(name="Запросы", description = "Методы, взаимодействующие с запросами на обмен билетов и поиск компании на мероприятие")
@RequestMapping(value = "request")
public class RequestController {
    private final RequestServiceImpl requestService;

    public RequestController(RequestServiceImpl requestService) {
        this.requestService = requestService;
    }

    @GetMapping(value = "/get/{id}", produces = {"application/json", "application/xml"})
    @Operation(summary = "Получаем экземпляр запроса по его Id")
    public RequestDTO getEvent(@PathVariable Long id) {
        return requestService.findOne(id).orElseThrow(EntityNotFoundException::new);
    }


    @GetMapping(value = "/get_all", produces = {"application/json", "application/xml"})
    @Operation(summary = "Отображаем все запросы")
    public List<RequestDTO> displayAllEvents() {
        return requestService.findAll();
    }

    @PostMapping(value = "/update", produces = {"application/json", "application/xml"})
    @Operation(summary = "Обновляем экземпляр пользователя")
    public RequestDTO updateRequest(@RequestBody RequestDTO request) {
        return requestService.update(request);
    }


    @DeleteMapping(value = "/delete/{id}",
            produces = {"application/json", "application/xml"})
    @Operation(summary = "Удаляем экземпляр пользователя по его Id")
    public void deleteRequest(@PathVariable Long id) {
        requestService.delete(id);
    }

}
