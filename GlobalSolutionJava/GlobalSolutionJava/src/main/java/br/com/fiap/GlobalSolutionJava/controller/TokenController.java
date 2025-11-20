package br.com.fiap.GlobalSolutionJava.controller;

import br.com.fiap.GlobalSolutionJava.domain.dto.request.LoginRequest;
import br.com.fiap.GlobalSolutionJava.domain.dto.response.LoginResponse;
import br.com.fiap.GlobalSolutionJava.exceptions.InvalidCredentialsException;
import br.com.fiap.GlobalSolutionJava.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Locale;

@Data
@AllArgsConstructor
@RestController
public class TokenController {

    private final JwtEncoder jwtEncoder;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final MessageSource messageSource;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest, Locale locale) {
        var user = userRepository.findByEmailUsuario(loginRequest.emailUsuario());

        if (user.isEmpty() || !user.get().isLoginCorrect(loginRequest, passwordEncoder)) {
            String message = messageSource.getMessage("auth.invalid.credentials", null, locale);
            throw new InvalidCredentialsException(message);
        }

        var now = Instant.now();
        var expiresIn = 300L;

        var claims = JwtClaimsSet.builder()
                .issuer("java")
                .subject(user.get().getIdUsuario().toString())
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiresIn))
                .build();

        var jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        return ResponseEntity.ok(new LoginResponse(jwtValue, expiresIn));
    }
}
