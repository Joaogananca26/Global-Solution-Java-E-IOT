package br.com.fiap.GlobalSolutionJava.service;

import br.com.fiap.GlobalSolutionJava.Integrations.ViaCepClient;
import br.com.fiap.GlobalSolutionJava.domain.Localizacao;
import br.com.fiap.GlobalSolutionJava.domain.User;
import br.com.fiap.GlobalSolutionJava.domain.dto.response.ViaCepResponse;
import br.com.fiap.GlobalSolutionJava.repository.LocalizacaoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LocalizacaoService {

    private final LocalizacaoRepository localizacaoRepository;
    private final ViaCepClient viaCepClient;

    public Localizacao persistLocation(String cep, User user) {

        ViaCepResponse viaCep = viaCepClient.getUserAddress(cep);

        Localizacao localizacao = new Localizacao(null, cep, viaCep.logradouro(), viaCep.bairro(),
                viaCep.uf(), viaCep.localidade(), user);

        user.setLocalizacao(localizacao);
        return localizacaoRepository.save(localizacao);
    }

}
