package com.cityconcert.service;

import com.cityconcert.domain.dto.UserDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service class for managing users.
 */
@Service
@Transactional
public interface UserService {
 UserDTO save(UserDTO userDTO);

    UserDTO getCurrentUser();



}
