package com.cityconcert.mapper;

import com.cityconcert.domain.dto.VenueDTO;
import com.cityconcert.domain.model.Venue;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Venue} and its DTO {@link VenueDTO}.
 */
@Mapper(componentModel = "spring")
public interface VenueMapper extends EntityMapper<VenueDTO, Venue> {
    Venue toEntity(VenueDTO v);

    VenueDTO toDto(Venue v);


}
