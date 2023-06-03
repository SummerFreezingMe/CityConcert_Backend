package com.cityconcert.mapper;

import com.cityconcert.domain.dto.UserDTO;
import com.cityconcert.domain.model.User;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link User} and its DTO called {@link UserDTO}.
 */

@Mapper(componentModel = "spring")
public interface UserMapper extends EntityMapper<UserDTO, User> {
    UserDTO toDto(User s);

    User toEntity(UserDTO s);
}

