package com.cityconcert.controller;

import com.cityconcert.domain.dto.UserDTO;
import com.cityconcert.service.impl.AuthServiceImpl;
import com.cityconcert.service.impl.UserServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
public class RegistrationController {

    private final UserServiceImpl userService;

    private final AuthServiceImpl authService;
    public RegistrationController(UserServiceImpl userService, AuthServiceImpl authService) {
        this.userService = userService;

        this.authService = authService;
    }



    @PostMapping("/registration")
    public Object addUser(@RequestBody UserDTO user) {

        if (user.getPassword().equals(user.getPasswordConfirm())){
        return userService.save(user);
    }
        return null;
    }

    @GetMapping("/login")
    public Object loginUser(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password) {

        return authService.login(username,password);
    }
}