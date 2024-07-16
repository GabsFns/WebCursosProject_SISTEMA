package com.example.testeSpringBoot.services;

import com.example.testeSpringBoot.models.UsuarioModel;
import com.example.testeSpringBoot.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioModel salvarUsuario(UsuarioModel usuarioModel) {
        return usuarioRepository.save(usuarioModel);
    }

    public UsuarioModel findByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }
}
