package br.com.fiap.GlobalSolutionJava.service;

import br.com.fiap.GlobalSolutionJava.domain.User;
import br.com.fiap.GlobalSolutionJava.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User salvar(User user, LocalDate dataNascimento) {
        user.setDataNascimentoUsuario(dataNascimento);
        return userRepository.save(user);
    }

}
