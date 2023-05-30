package com.cityconcert.service;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthService extends UserDetailsService {

    Object login(String login, String password);

}
