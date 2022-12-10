package com.api.lores.common;

public class SecurityConstants {

    public static final String SECRET = "ADICIONE_SUA_SECRET_AQUI";
    public static final String ISSUER = "lores";
    public static final long EXPIRATION_TIME = 3_600_000; //60 minutos
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String AUTH_ENDPOINT = "/api/auth";
    public static final String REGISTER_ENDPOINT = "/api/users";

    public static String[] SWAGGER_ENDPOINTS = {
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            "/swagger-ui/**",
            "/configuration/**",
            "/v3/api-docs/**",
            "/swagger-ui/**"
    };
}