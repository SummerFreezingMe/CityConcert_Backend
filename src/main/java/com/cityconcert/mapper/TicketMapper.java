package com.cityconcert.mapper;

import com.cityconcert.domain.Event;
import com.cityconcert.domain.Ticket;
import com.cityconcert.domain.User;
import com.cityconcert.domain.dto.EventDTO;
import com.cityconcert.domain.dto.TicketDTO;
import com.cityconcert.domain.dto.UserDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Ticket} and its DTO {@link TicketDTO}.
 */
@Mapper(componentModel = "spring")
public interface TicketMapper extends EntityMapper<TicketDTO, Ticket> {
    TicketDTO toDto(Ticket t);

    Ticket toEntity(TicketDTO t);
}
