package com.api.lores.service.auth;

import com.api.lores.dto.LoginDto;
import com.api.lores.dto.TokenDto;
import com.api.lores.service.token.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final AuthenticationConfiguration authenticationConfiguration;
    private final TokenService tokenService;

    @Autowired
    public AuthServiceImpl(AuthenticationConfiguration authenticationConfiguration, TokenService tokenService) {
        this.authenticationConfiguration = authenticationConfiguration;
        this.tokenService = tokenService;
    }

    @Override
    public TokenDto login(LoginDto dto) throws Exception {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword());

        Authentication authentication = authenticationConfiguration.getAuthenticationManager().authenticate(usernamePasswordAuthenticationToken);

        String token = tokenService.generateToken(authentication);

        TokenDto tokenDto = new TokenDto();
        tokenDto.setToken(token);
        tokenDto.setRole(authentication.getAuthorities());

        return tokenDto;
    }
}