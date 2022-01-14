package br.com.mentoria.mentoriajava.controller;

import br.com.mentoria.mentoriajava.model.Produto;
import br.com.mentoria.mentoriajava.repository.ProdutoRepository;
import br.com.mentoria.mentoriajava.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @ResponseStatus (HttpStatus.CREATED)
    @PostMapping("/produtos")
    public Produto salvarProduto (@RequestBody Produto produto){
        return produtoService.salvarProduto(produto);
    }

    @GetMapping("/produtos/{id}")
    public Produto retornarProduto(@PathVariable Long id){
        return produtoService.buscarUmProduto(id);
    }

    @GetMapping("/produtos")
    public List<Produto> retornarTodosProdutos(){
        return produtoService.buscarTodosProdutos();
    }

    @PutMapping("/produtos/{id}")
    public Produto atualizaProduto(@PathVariable Long id, @RequestBody Produto produto){
        return produtoService.atualizarProdutos(id, produto);
    }

    @DeleteMapping("/produtos/{id}")
    @ResponseStatus (HttpStatus.NO_CONTENT)
    public void deletarProduto (@PathVariable Long id){
        produtoService.deletarProduto(id);
    }
}
