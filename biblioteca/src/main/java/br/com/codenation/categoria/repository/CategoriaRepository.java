package br.com.codenation.categoria.repository;

import br.com.codenation.categoria.model.Categoria;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoriaRepository extends CrudRepository<Categoria, Long>{

    List<Categoria> findAll();
}
