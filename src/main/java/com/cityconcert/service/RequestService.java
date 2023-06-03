package com.cityconcert.service;

import com.cityconcert.domain.dto.RequestDTO;
import com.cityconcert.domain.model.Request;

import java.util.List;
import java.util.Optional;
/**
 * Service Interface for managing {@link Request}.
 */
public interface RequestService {
    RequestDTO save(RequestDTO exchangeRequest);

    RequestDTO update(RequestDTO request);

    Optional<RequestDTO> findOne(Long id);

    void delete(Long id);

    List<RequestDTO> findAllByType(String type);
}
