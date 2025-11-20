package br.com.fiap.GlobalSolutionJava.controller;

import br.com.fiap.GlobalSolutionJava.domain.User;
import br.com.fiap.GlobalSolutionJava.domain.dto.request.CreateUserDTO;
import br.com.fiap.GlobalSolutionJava.repository.UserRepository;
import br.com.fiap.GlobalSolutionJava.service.UserService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@RestController
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Transactional
    @PostMapping("/users")
    @CacheEvict(value = "users", allEntries = true)
    public ResponseEntity<Void> newUser(@Valid @RequestBody  CreateUserDTO dto) {

        var userDb = userRepository.findByEmailUsuario(dto.emailUsuario());

        if (userDb.isPresent()) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        var user = new User();
        LocalDate dataNascimento = LocalDate.of(dto.ano(), dto.mes(), dto.dia());
        user.setEmailUsuario(dto.emailUsuario());
        user.setSenhaUsuario(passwordEncoder.encode(dto.senhaUsuario()));
        user.setNomeUsuario(dto.nomeUsuario());


        userService.salvar(user, dataNascimento);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/users")
    @Cacheable(value = "users", key = "#pageable.pageNumber + '-' + #pageable.pageSize + '-' + #pageable.sort")
    public ResponseEntity<Page<User>> listUsers(
            @PageableDefault(size = 10, sort = "emailUsuario") Pageable pageable
    ) {
        var users = userRepository.findAll(pageable);
        return ResponseEntity.ok(users);
    }
}
