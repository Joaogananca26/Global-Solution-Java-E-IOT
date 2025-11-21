package br.com.fiap.GlobalSolutionJava.repository;

import br.com.fiap.GlobalSolutionJava.domain.Localizacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocalizacaoRepository extends JpaRepository<Localizacao, String> {
}
