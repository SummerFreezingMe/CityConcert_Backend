package com.cityconcert.controller;

import com.cityconcert.domain.dto.UserDTO;
import com.cityconcert.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/users")
    public List<UserDTO> userList() {
        return userService.findAll();
    }


}