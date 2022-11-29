package com.api.lores.controller;

import com.api.lores.dto.UserDto;
import com.api.lores.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public String create(@RequestBody @Valid UserDto dto) {
        return userService.create(dto);
    }
}