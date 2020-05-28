package br.com.codenation.livro.repository;


import br.com.codenation.categoria.model.Categoria;
import br.com.codenation.livro.model.Livro;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface LivroRepository extends CrudRepository<Livro, Long> {

    List<Livro> findAll();

    List<Livro> findByTitulo(String titulo);

    List<Livro> findByTituloContaining(String titulo);

    List<Livro> findByCategorias(Categoria categoria);

    @Query("from Livro livro where livro.categorias is not empty")
    List<Livro> findComCategoria();


    @Query(value = "select * from LIVRO livro " +
            "INNER JOIN LIVRO_CATEGORIA cl " +
            "ON livro.id = cl.id_livro " +
            "INNER JOIN categoria c " +
            "ON c.id = cl.id_categoria " +
            "where c.nome like %:nomeCategoria%", nativeQuery = true)
    List<Livro> findByNomeCategoria(@Param("nomeCategoria") String nomeCategoria);
}
