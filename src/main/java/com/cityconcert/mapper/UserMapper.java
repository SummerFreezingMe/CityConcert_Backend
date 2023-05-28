package com.cityconcert.mapper;

import com.cityconcert.domain.model.User;
import com.cityconcert.domain.dto.UserDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link User} and its DTO called {@link UserDTO}.
 * <p>
 * Normal mappers are generated using MapStruct, this one is hand-coded as MapStruct
 * support is still in beta, and requires a manual step with an IDE.
 */

    @Mapper(componentModel = "spring")
    public interface UserMapper extends EntityMapper<UserDTO, User> {
        UserDTO toDto(User s);

        User toEntity(UserDTO s);
    }

