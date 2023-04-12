package com.cityconcert.service.impl;

import com.cityconcert.domain.Request;
import com.cityconcert.domain.dto.RequestDTO;
import com.cityconcert.mapper.RequestMapper;
import com.cityconcert.repository.RequestRepository;
import com.cityconcert.service.RequestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RequestServiceImpl implements RequestService {

    private final Logger log = LoggerFactory.getLogger(RequestServiceImpl.class);

    private final RequestRepository requestRepository;

    private final RequestMapper requestMapper;

    public RequestServiceImpl(RequestRepository requestRepository, RequestMapper requestMapper) {
        this.requestRepository = requestRepository;
        this.requestMapper = requestMapper;
    }

    @Override
    public RequestDTO save(RequestDTO requestDTO) {
        log.debug("Request to save request : {}", requestDTO);
        Request request = requestMapper.toEntity(requestDTO);
         requestRepository.save(request);
        return requestMapper.toDto(request);
    }

    @Override
    public RequestDTO update(RequestDTO requestDTO) {
        log.debug("Request to update request : {}", requestDTO);
        Request request = requestMapper.toEntity(requestDTO);
        request = requestRepository.save(request);
        return requestMapper.toDto(request);
    }

    @Override
    public Optional<RequestDTO> findOne(Long id) {
        log.debug("Request to get request : {}", id);
        return requestRepository.findById(id).map(requestMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete request : {}", id);
        requestRepository.deleteById(id);
    }

    @Override
    public List<RequestDTO> findAll() {
        log.debug("Request to get all requests");
        return requestRepository.findAll().stream().map(requestMapper::toDto).collect(Collectors.toCollection(LinkedList::new));

    }
}
