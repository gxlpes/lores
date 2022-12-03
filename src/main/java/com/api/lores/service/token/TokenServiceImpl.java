package com.api.lores.service.token;

import com.api.lores.model.UserModel;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

import static com.api.lores.common.SecurityConstants.*;

@Service
public class TokenServiceImpl implements TokenService {

    @Override
    public String generateToken(Authentication authentication) {
        UserModel userModel = (UserModel) authentication.getPrincipal();

        Date now = new Date();
        Date exp = new Date(now.getTime() + EXPIRATION_TIME);

        var jwt = Jwts.builder().setIssuer(ISSUER)
                .setSubject(userModel.getUserId().toString())
                .setIssuedAt(now)
                .setExpiration(exp)
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();

        return jwt;
    }

    @Override
    public boolean isTokenValid(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public String getTokenId(String token) {
        Claims body = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
        return body.getSubject();
    }
}