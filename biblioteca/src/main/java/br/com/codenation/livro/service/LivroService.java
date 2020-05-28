package br.com.codenation.livro.service;

import br.com.codenation.categoria.model.Categoria;
import br.com.codenation.livro.model.Livro;

import java.util.List;
import java.util.Optional;

public interface LivroService {

    List<Livro> findAll();

    List<Livro> findByCategoria(Long idCategoria);

    Optional<Livro> findById(Long id);

    Livro save(Livro livro);

    void deleteById(Long id);

    List<Livro> findByNome(String nome);

    List<Livro> findByNomeCategoria(String nomeCategoria);

    List<Livro> findComCategorias();
}
