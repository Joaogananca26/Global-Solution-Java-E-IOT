package br.com.fiap.GlobalSolutionJava.controller;

import br.com.fiap.GlobalSolutionJava.config.security.TokenService;
import br.com.fiap.GlobalSolutionJava.domain.User;
import br.com.fiap.GlobalSolutionJava.domain.dto.request.LoginRequest;
import br.com.fiap.GlobalSolutionJava.domain.dto.response.LoginResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("login")
@AllArgsConstructor
public class AuthController {

    private AuthenticationManager authenticationManager;

    private TokenService tokenService;

    @PostMapping
    public ResponseEntity login(@RequestBody @Valid LoginRequest loginRequest) {

        Authentication token = new UsernamePasswordAuthenticationToken(loginRequest.emailUsuario(), loginRequest.senhaUsuario());
        Authentication auth = authenticationManager.authenticate(token); // loadUserByUsername (valida se o usuario existe)

        return ResponseEntity.ok(new LoginResponse(tokenService.gerarToken((User) auth.getPrincipal())));
    }
}
