package br.com.codenation.livro.controller;

import br.com.codenation.livro.model.Livro;
import br.com.codenation.livro.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livro")
public class LivroController {

    @Autowired
    private LivroService livroService;

    @PostMapping
    public Livro save(@RequestBody Livro livro) {
        return this.livroService.save(livro);
    }

    @GetMapping
    public Iterable<Livro> findAll() {
        return this.livroService.findAll();
    }

    @GetMapping("/{id}")
    public Livro findById(@PathVariable("id") Long id) {
        return this.livroService.findById(id).get();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        this.livroService.deleteById(id);
    }

    @GetMapping("/byCategoria/{id}")
    public List<Livro> findByCategoria(@PathVariable("id") Long idCategoria) {
        return this.livroService.findByCategoria(idCategoria);
    }

    @GetMapping("/byNome/{nome}")
    public List<Livro> findByNome(@PathVariable("nome") String nome) {
        return this.livroService.findByNome(nome);
    }

    @GetMapping("/byNomeCategoria/{nome}")
    public List<Livro> findByNomeCategoria(@PathVariable("nome") String nomeCategoria) {
        return this.livroService.findByNomeCategoria(nomeCategoria);
    }

    @GetMapping("/comCategorias")
    public List<Livro> findComCategoria(){
        return this.livroService.findComCategorias();
    }

}
