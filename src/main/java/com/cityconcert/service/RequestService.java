package com.cityconcert.service;

import com.cityconcert.domain.dto.RequestDTO;

import java.util.List;
import java.util.Optional;

public interface RequestService {
    RequestDTO save(RequestDTO exchangeRequest);

    RequestDTO update(RequestDTO request);

    Optional<RequestDTO> findOne(Long id);

    void delete(Long id);

    List<RequestDTO> findAllByType(String type);
}
