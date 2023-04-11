package com.cityconcert.controller;


import com.cityconcert.domain.dto.UserDTO;
import com.cityconcert.service.impl.UserServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserServiceImpl usi;

    public UserController(UserServiceImpl usi) {
        this.usi = usi;
    }
    @PostMapping(value = "/add", produces = {"application/json", "application/xml"})
    public UserDTO addUser(@RequestBody UserDTO user) {
        return usi.save(user);
    }

    @PostMapping(value = "/update/{id}", produces = {"application/json", "application/xml"})
    public UserDTO updateUser(@RequestBody UserDTO user) {
        return usi.updateUser(user);
    }

    @GetMapping(value = "/get/{id}", produces = {"application/json", "application/xml"})
    public UserDTO getUser(@PathVariable Long id) {
        return usi.findOne(id).get();
    }

    @DeleteMapping(value = "/delete/{id}",
            produces = {"application/json", "application/xml"})
    public void deleteUser(@PathVariable String id) {
        usi.deleteUser(id);
    }

    @GetMapping(value = "/current", produces = {"application/json", "application/xml"})
    public UserDTO currentUser() {
        return usi.getCurrentUser();
    }

}
