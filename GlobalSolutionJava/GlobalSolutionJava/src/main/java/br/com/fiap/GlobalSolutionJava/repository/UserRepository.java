package br.com.fiap.GlobalSolutionJava.repository;

import br.com.fiap.GlobalSolutionJava.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByEmailUsuario(String emailUsuario);

}
