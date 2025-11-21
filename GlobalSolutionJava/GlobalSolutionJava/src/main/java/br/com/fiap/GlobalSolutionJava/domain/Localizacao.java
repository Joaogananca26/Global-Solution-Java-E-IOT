package br.com.fiap.GlobalSolutionJava.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Localizacao {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_localizacao")
    private String idLocalizacao;
    @Column(name = "cep_localizacao", nullable = false)
    private String cepLocalizacao;
    @Column(name = "logradouro_localizacao")
    private String logradouroLocalizacao;
    @Column(name = "bairro_localizacao")
    private String bairroLocalizacao;
    @Column(name = "estado_localizacao")
    private String estadoLocalizacao;
    @Column(name = "regiao_localizacao")
    private String regiaoLocalizacao;

    @OneToOne(mappedBy = "localizacao", cascade = CascadeType.ALL)
    private User user;
}