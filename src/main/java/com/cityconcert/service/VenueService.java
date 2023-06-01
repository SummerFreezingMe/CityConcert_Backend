package com.cityconcert.service;

import com.cityconcert.domain.dto.VenueDTO;
import com.cityconcert.domain.model.Venue;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Venue}.
 */
public interface VenueService {
    /**
     * Save a venue.
     *
     * @param venueDTO the entity to save.
     * @return the persisted entity.
     */
    VenueDTO save(VenueDTO venueDTO);

    /**
     * Updates a venue.
     *
     * @param venueDTO the entity to update.
     * @return the persisted entity.
     */
    VenueDTO update(VenueDTO venueDTO);

    /**
     * Partially updates a venue.
     *
     * @param venueDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<VenueDTO> partialUpdate(VenueDTO venueDTO);

    /**
     * Get all the venues.
     *
     * @return the list of entities.
     */
    List<VenueDTO> findAll();

    /**
     * Get the "id" venue.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
   VenueDTO findOne(Long id);

    /**
     * Delete the "id" venue.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
