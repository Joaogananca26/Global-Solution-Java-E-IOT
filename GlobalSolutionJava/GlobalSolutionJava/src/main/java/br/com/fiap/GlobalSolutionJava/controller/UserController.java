package br.com.fiap.GlobalSolutionJava.controller;

import br.com.fiap.GlobalSolutionJava.domain.User;
import br.com.fiap.GlobalSolutionJava.domain.dto.request.CreateUser;
import br.com.fiap.GlobalSolutionJava.domain.dto.request.UpdateUser;
import br.com.fiap.GlobalSolutionJava.domain.dto.response.ListUsersResponse;
import br.com.fiap.GlobalSolutionJava.domain.dto.response.MessageResponse;
import br.com.fiap.GlobalSolutionJava.repository.UserRepository;
import br.com.fiap.GlobalSolutionJava.service.LocalizacaoService;
import br.com.fiap.GlobalSolutionJava.service.UserService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import java.time.LocalDate;
import java.util.Locale;

@AllArgsConstructor
@RestController
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final LocalizacaoService localizacaoService;

    @Transactional
    @PostMapping("/users")
    @CacheEvict(value = "users", allEntries = true)
    public ResponseEntity<Void> newUser(@Valid @RequestBody CreateUser dto) {

        var userDb = userRepository.findByEmailUsuario(dto.emailUsuario());

        if (userDb.isPresent()) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        var user = new User();
        LocalDate dataNascimento = LocalDate.of(dto.ano(), dto.mes(), dto.dia());
        user.setEmailUsuario(dto.emailUsuario());
        user.setSenhaUsuario(passwordEncoder.encode(dto.senhaUsuario()));
        user.setNomeUsuario(dto.nomeUsuario());


        User savedUser = userService.salvar(user, dataNascimento);

        localizacaoService.persistLocation(dto.cepUsuario(), savedUser);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/users")
    @Cacheable(value = "users", key = "#pageable.pageNumber + '-' + #pageable.pageSize + '-' + #pageable.sort")
    public ResponseEntity<Page<ListUsersResponse>> listUsers(
            @PageableDefault(size = 10, sort = "emailUsuario") Pageable pageable
    ) {
        var usersPage = userRepository.findAll(pageable);

        var dtoPage = usersPage.map(user -> new ListUsersResponse(
                user.getIdUsuario(),
                user.getEmailUsuario(),
                user.getNomeUsuario(),
                user.getDataNascimentoUsuario()
        ));

        return ResponseEntity.ok(dtoPage);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<ListUsersResponse> getById(@PathVariable String id) {
        return userRepository.findById(id)
                .map(user -> ResponseEntity.ok(
                        new ListUsersResponse(
                                user.getIdUsuario(),
                                user.getEmailUsuario(),
                                user.getNomeUsuario(),
                                user.getDataNascimentoUsuario()
                        )
                ))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<MessageResponse> updateUser(
            @PathVariable String id,
            @RequestBody @Valid UpdateUser dto,
            Locale locale
    ) {
        userService.updateUser(id, dto, locale);

        return ResponseEntity.ok(
                new MessageResponse("Usuário atualizado com sucesso")
        );
    }
}
