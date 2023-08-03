package com.PI.ProntuarioEletronico.services.securityServices;

import com.PI.ProntuarioEletronico.models.UserModel;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${api.security.token.secrete")
    private String secrete;

    public String generateToken(UserModel user){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secrete);
            String token = JWT.create()
                    .withIssuer("pront-api")
                    .withSubject(user.getEmail())
                    .withExpiresAt(LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.of("-03:00")))
                    .sign(algorithm);

            return token;

        }catch (JWTCreationException exception){
            throw  new RuntimeException("error", exception);
        }
    }
}
