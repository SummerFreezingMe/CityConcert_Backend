package com.cityconcert.controller;

import com.cityconcert.domain.dto.UserDTO;
import com.cityconcert.mapper.UserMapper;
import com.cityconcert.service.impl.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {

    private final UserServiceImpl userService;

    private UserMapper userMapper;

    public RegistrationController(UserServiceImpl userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }



    @PostMapping("/registration")
    public Object addUser(@RequestBody UserDTO user) {

        if (user.getPassword().equals(user.getPasswordConfirm())){
        return userService.save(user);
    }
        return null;
    }

    @PostMapping("/login")
    public Object login(@RequestBody UserDTO user) {

        if (user.getPassword().equals(user.getPasswordConfirm())){
            return userService.save(user);
        }
        return null;
    }
}