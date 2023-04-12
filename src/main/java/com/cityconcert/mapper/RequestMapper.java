package com.cityconcert.mapper;


import com.cityconcert.domain.Request;
import com.cityconcert.domain.dto.RequestDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Request} and its DTO {@link RequestDTO}.
 */
@Mapper(componentModel = "spring")
public interface RequestMapper extends EntityMapper<RequestDTO, Request> {
    RequestDTO toDto(Request t);

    Request toEntity(RequestDTO t);
}
