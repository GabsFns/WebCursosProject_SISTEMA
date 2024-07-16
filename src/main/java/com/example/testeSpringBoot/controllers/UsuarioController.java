package com.example.testeSpringBoot.controllers;


import com.example.testeSpringBoot.dto.UsuarioLoginDTO;
import com.example.testeSpringBoot.models.UsuarioModel;
import com.example.testeSpringBoot.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.testeSpringBoot.security.JwtUtil;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/cadastro")
    public ResponseEntity<UsuarioModel> cadastrarUsuario(@Valid @RequestBody UsuarioModel usuarioModel) {
        UsuarioModel novoUsuario = usuarioService.salvarUsuario(usuarioModel);
        return new ResponseEntity<>(novoUsuario, HttpStatus.CREATED);
    }



    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<?> login(@RequestParam("email") String email,
                                   @RequestParam("senha") String senha) {
        // Validação básica
        if (email == null || senha == null) {
            return ResponseEntity.badRequest().body("Email e senha são obrigatórios");
        }

        // Lógica de autenticação
        UsuarioModel usuario = usuarioService.findByEmail(email);
        if (usuario == null || !usuario.getSenha().equals(senha)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas");
        }

        // Autenticação bem-sucedida, retornar token JWT ou redirecionar conforme necessário
        // Exemplo de retorno de token JWT
        String token = jwtUtil.gerarToken(usuario.getIdUsuario(), usuario.getEmail());
        return ResponseEntity.ok().body(token);
    }
}
