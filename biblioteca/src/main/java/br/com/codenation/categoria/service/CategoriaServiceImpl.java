package br.com.codenation.categoria.service;

import br.com.codenation.categoria.model.Categoria;
import br.com.codenation.categoria.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaServiceImpl implements CategoriaService{

    @Autowired
    private CategoriaRepository categoriaRepository;


    @Override
    public Optional<Categoria> findById(Long id) {
        return this.categoriaRepository.findById(id);
    }

    @Override
    public List<Categoria> findAll() {
        return this.categoriaRepository.findAll();
    }

    @Override
    public Categoria save(Categoria categoria) {
        return this.categoriaRepository.save(categoria);
    }
}
