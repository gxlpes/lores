package com.api.lores.controller;

import com.api.lores.dto.LoginDto;
import com.api.lores.dto.TokenDto;
import com.api.lores.service.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.api.lores.common.SecurityConstants.AUTH_ENDPOINT;

@RestController
@CrossOrigin
@RequestMapping(AUTH_ENDPOINT)
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping
    public TokenDto login(@RequestBody @Valid LoginDto dto) throws Exception {
        return authService.login(dto);
    }
}