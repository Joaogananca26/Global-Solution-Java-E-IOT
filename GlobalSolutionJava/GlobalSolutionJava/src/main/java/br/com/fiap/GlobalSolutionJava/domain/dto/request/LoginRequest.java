package br.com.fiap.GlobalSolutionJava.domain.dto.request;


import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;

public record LoginRequest(

        @NotBlank(message = "{user.email.notblank}")
        String emailUsuario,

        @NotBlank(message = "{user.password.notblank}")
        String senhaUsuario

) {}
