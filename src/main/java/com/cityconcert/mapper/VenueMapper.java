package com.cityconcert.mapper;

import com.cityconcert.domain.Event;
import com.cityconcert.domain.Venue;
import com.cityconcert.domain.dto.EventDTO;
import com.cityconcert.domain.dto.VenueDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Venue} and its DTO {@link VenueDTO}.
 */
@Mapper(componentModel = "spring")
public interface VenueMapper extends EntityMapper<VenueDTO, Venue> {
    Venue toEntity(VenueDTO v);
    VenueDTO toDto(Venue v);


}
