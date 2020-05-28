package br.com.codenation.leitor.model;

import br.com.codenation.avaliacao.model.Avaliacao;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Leitor {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull
    @NotBlank
    private String nome;

    @OneToMany
    private List<Avaliacao> avaliacoes;

}
