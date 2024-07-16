package com.example.testeSpringBoot.security;

import com.example.testeSpringBoot.models.UsuarioModel;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;


import java.util.Date;
import java.util.UUID;

@Component
public class JwtUtil {
    private static final String SECRET_KEY = "chave-secreta"; // Chave secreta para assinatura do token

    public String gerarToken(UUID id, String email) {
        // Implementação da geração do token JWT
        // Exemplo simplificado:
        return Jwts.builder()
                .setSubject(email)
                .claim("userId", id)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 24 horas
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }
}
