package com.cityconcert.service.impl;

import com.cityconcert.domain.Venue;
import com.cityconcert.repository.VenueRepository;
import com.cityconcert.service.VenueService;
import com.cityconcert.domain.dto.VenueDTO;
import com.cityconcert.mapper.VenueMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

/**
 * Service Implementation for managing {@link Venue}.
 */
@Service
@Transactional
public class VenueServiceImpl implements VenueService {

    private final Logger log = LoggerFactory.getLogger(VenueServiceImpl.class);

    private final VenueRepository venueRepository;

    private final VenueMapper venueMapper;

    public VenueServiceImpl(VenueRepository venueRepository, VenueMapper venueMapper) {
        this.venueRepository = venueRepository;
        this.venueMapper = venueMapper;
    }

    @Override
    public VenueDTO save(VenueDTO venueDTO) {
        log.debug("Request to save Venue : {}", venueDTO);
        Venue venue = venueMapper.toEntity(venueDTO);
        venue = venueRepository.save(venue);
        return venueMapper.toDto(venue);
    }

    @Override
    public VenueDTO update(VenueDTO venueDTO) {
        log.debug("Request to update Venue : {}", venueDTO);
        Venue venue = venueMapper.toEntity(venueDTO);
        venue = venueRepository.save(venue);
        return venueMapper.toDto(venue);
    }

    @Override
    public Optional<VenueDTO> partialUpdate(VenueDTO venueDTO) {
        log.debug("Request to partially update Venue : {}", venueDTO);

        return venueRepository
            .findById(venueDTO.getId())
            .map(existingVenue -> {
                venueMapper.partialUpdate(existingVenue, venueDTO);

                return existingVenue;
            })
            .map(venueRepository::save)
            .map(venueMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<VenueDTO> findAll() {
        log.debug("Request to get all Venues");
        return venueRepository.findAll().stream().map(venueMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public VenueDTO findOne(Long id) {
        log.debug("Request to get Venue : {}", id);
        Venue venue =venueRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return venueMapper.toDto(venue);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Venue : {}", id);
        venueRepository.deleteById(id);
    }
}
