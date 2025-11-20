package br.com.fiap.GlobalSolutionJava.domain.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CreateUserDTO(
        @NotBlank(message = "{user.username.notblank}")
        String usuario,

        @NotBlank(message = "{user.password.notblank}")
        String senha
) {}
