package com.cityconcert.controller;


import com.cityconcert.domain.dto.UserDTO;
import com.cityconcert.service.impl.UserServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    private final UserServiceImpl usi;

    public UserController(UserServiceImpl usi) {
        this.usi = usi;
    }
    @PostMapping(value = "/add_user", produces = {"application/json", "application/xml"})
    public UserDTO addUser(@RequestBody UserDTO user) {
        return usi.save(user);
    }

    @GetMapping(value = "/get_user/{id}", produces = {"application/json", "application/xml"})
    public UserDTO getUser(@PathVariable Long id) {
        return usi.findOne(id).get();
    }

    @DeleteMapping(value = "/delete_user/{id}",
            produces = {"application/json", "application/xml"})
    public void deleteEvent(@PathVariable String id) {
        usi.deleteUser(id);
    }
}
