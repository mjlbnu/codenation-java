package br.com.codenation.categoria.controller;

import br.com.codenation.categoria.model.Categoria;
import br.com.codenation.categoria.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {


    @Autowired
    private CategoriaService categoriaService;


    @GetMapping
    public List<Categoria> findAll(){
        return this.categoriaService.findAll();
    }

    @PostMapping
    public Categoria save(@RequestBody Categoria categoria){
        return this.categoriaService.save(categoria);
    }

}
