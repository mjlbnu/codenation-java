package br.com.codenation.avaliacao.service;

import br.com.codenation.avaliacao.model.Avaliacao;

import java.util.List;

public interface AvaliacaoService {

    List<Avaliacao> findAll();

    Avaliacao save(Avaliacao avaliacao);

}
