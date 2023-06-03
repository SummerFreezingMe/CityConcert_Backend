package com.cityconcert.mapper;

import com.cityconcert.domain.dto.TicketDTO;
import com.cityconcert.domain.model.Ticket;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Ticket} and its DTO {@link TicketDTO}.
 */
@Mapper(componentModel = "spring")
public interface TicketMapper extends EntityMapper<TicketDTO, Ticket> {
    TicketDTO toDto(Ticket t);

    Ticket toEntity(TicketDTO t);
}
