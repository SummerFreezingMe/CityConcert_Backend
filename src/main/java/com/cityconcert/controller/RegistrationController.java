package com.cityconcert.controller;

import com.cityconcert.domain.dto.UserDTO;
import com.cityconcert.service.impl.AuthServiceImpl;
import com.cityconcert.service.impl.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name="Вход/Регистрация",description = " Вход и регистрация пользователями")
public class RegistrationController {

    private final UserServiceImpl userService;
    private final AuthServiceImpl authService;
    public RegistrationController(UserServiceImpl userService, AuthServiceImpl authService) {
        this.userService = userService;
        this.authService = authService;
    }
    @PostMapping("/registration")
    @Operation(summary = "Регистрация нового пользователя")
    public Object addUser(@RequestBody UserDTO user) {

        if (user.getPassword().equals(user.getPasswordConfirm())){
        return userService.save(user);
    }
        return null;
    }

    @GetMapping("/login")
    @Operation(summary = "Вход в систему по имени пользователя и паролю")
    public Object loginUser(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password) {

        return authService.login(username,password);
    }
}