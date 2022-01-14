package br.com.mentoria.mentoriajava.controller;

import br.com.mentoria.mentoriajava.model.Categoria;
import br.com.mentoria.mentoriajava.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @ResponseStatus (HttpStatus.CREATED)
    @PostMapping ("/categorias")
    public Categoria salvarCategoria(@RequestBody Categoria categoria){
        return categoriaService.salvarCategoria(categoria);
    }

    @GetMapping ("/categorias/{id}")
    public Categoria retornarUmaCategoria(@PathVariable Long id){
        return categoriaService.buscarUmaCategoria(id);
    }

    @GetMapping ("/categorias")
    public List<Categoria> retronarTodasCategorias(){
        return categoriaService.buscarCategorias();
    }

    @PutMapping ("/categorias/{id}")
    public Categoria atualizarCategoria(@PathVariable Long id, @RequestBody Categoria categoriaAtualizada){
        return categoriaService.atualizarCategoria(id, categoriaAtualizada);
    }

    @DeleteMapping("/categorias/{id}")
    @ResponseStatus (HttpStatus.NO_CONTENT)
    public void deletarCategoria(@PathVariable Long id){
        categoriaService.deletarCategoria(id);
    }
}
