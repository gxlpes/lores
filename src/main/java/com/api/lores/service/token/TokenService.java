package com.api.lores.service.token;

import org.springframework.security.core.Authentication;

public interface TokenService {

    String generateToken(Authentication authentication);

    boolean isTokenValid(String token);

    String getTokenId(String token);
}