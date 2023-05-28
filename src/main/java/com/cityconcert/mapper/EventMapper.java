package com.cityconcert.mapper;

import com.cityconcert.domain.model.Event;
import com.cityconcert.domain.dto.EventDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Event} and its DTO {@link EventDTO}.
 */
@Mapper(componentModel = "spring")
public interface EventMapper extends EntityMapper<EventDTO, Event> {
   EventDTO toDto(Event s);

    Event toEntity(EventDTO s);
}
