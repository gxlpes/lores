package com.api.lores.service.auth;

import com.api.lores.dto.LoginDto;
import com.api.lores.dto.TokenDto;

public interface AuthService {

    public TokenDto login(LoginDto dto) throws Exception;
}