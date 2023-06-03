package com.cityconcert.service;

import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Service Interface for managing authentication.
 */

public interface AuthService extends UserDetailsService {

    Object login(String login, String password);

}
