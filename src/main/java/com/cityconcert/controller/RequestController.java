package com.cityconcert.controller;

import com.cityconcert.domain.dto.RequestDTO;
import com.cityconcert.service.impl.RequestServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/request")
public class RequestController {
    private final RequestServiceImpl requestService;

    public RequestController(RequestServiceImpl requestService) {
        this.requestService = requestService;
    }

    @PostMapping(value = "/add", produces = {"application/json", "application/xml"})
    public RequestDTO addRequest(@RequestBody RequestDTO request) {
        return requestService.save(request);
    }

    @PostMapping(value = "/update", produces = {"application/json", "application/xml"})
    public RequestDTO updateRequest(@RequestBody RequestDTO request) {
        return requestService.update(request);
    }

    @GetMapping(value = "/get/{id}", produces = {"application/json", "application/xml"})
    public RequestDTO getRequest(@PathVariable Long id) {
        return requestService.findOne(id).get();
    }

    @DeleteMapping(value = "/delete/{id}",
            produces = {"application/json", "application/xml"})
    public void deleteRequest(@PathVariable Long id) {
        requestService.delete(id);
    }

    @GetMapping(value = "/get_all", produces = {"application/json", "application/xml"})
    public List<RequestDTO> displayAllRequests() {
        return requestService.findAll();
    }

}