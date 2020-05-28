package br.com.codenation.avaliacao.repository;

import br.com.codenation.avaliacao.model.Avaliacao;
import br.com.codenation.avaliacao.model.AvaliacaoIdentity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AvaliacaoRepository extends CrudRepository<Avaliacao, AvaliacaoIdentity>{

    List<Avaliacao> findAll();
}
