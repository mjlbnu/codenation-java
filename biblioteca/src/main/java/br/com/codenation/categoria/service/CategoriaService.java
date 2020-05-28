package br.com.codenation.categoria.service;

import br.com.codenation.categoria.model.Categoria;

import java.util.List;
import java.util.Optional;

public interface CategoriaService {

    Optional<Categoria> findById(Long id);

    List<Categoria> findAll();

    Categoria save(Categoria categoria);
}
