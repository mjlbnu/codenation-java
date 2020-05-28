package br.com.codenation.avaliacao.service;

import br.com.codenation.avaliacao.model.Avaliacao;
import br.com.codenation.avaliacao.repository.AvaliacaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvaliacaoServiceImpl implements AvaliacaoService {

    private AvaliacaoRepository avaliacaoRepository;

    @Override
    public List<Avaliacao> findAll() {
        return this.avaliacaoRepository.findAll();
    }

    @Override
    public Avaliacao save(Avaliacao avaliacao) {
        return this.avaliacaoRepository.save(avaliacao);
    }

}
