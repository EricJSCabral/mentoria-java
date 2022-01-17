package br.com.mentoria.mentoriajava.service;

import br.com.mentoria.mentoriajava.exceptions.ProdutoJaCadastrado;
import br.com.mentoria.mentoriajava.exceptions.ProdutoNaoEncontrado;
import br.com.mentoria.mentoriajava.model.Produto;
import br.com.mentoria.mentoriajava.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public Produto salvarProduto(Produto produto){
        return produtoRepository.save(produto);
    }

    public List<Produto> buscarTodosProdutos(Long id, Produto produto){
        Optional<Produto> teste = produtoRepository.findById(id);
        if (teste.isPresent()) {
            throw new ProdutoJaCadastrado("Produto já cadastrado no sistema", id);
        } else {
            Produto produtos = new Produto(produto.getId(), produto.getNome(), produto.getPreco(), produto.getCategoria());
            produtoRepository.save(produto);
            return (List<Produto>) ResponseEntity.ok().body(produtos);
        }
    }

    public Produto buscarUmProduto(Long id){
        Produto produto = produtoRepository.findById(id).get();
        if(produto == null){
            throw new ProdutoNaoEncontrado("Produto não encontrado no sistema", produto.getId());
        }
        return produtoRepository.findById(id).get();
    }


    public Produto atualizarProdutos(Long id, Produto produtoAtualizado){
        Optional<Produto> produtoExistente = produtoRepository.findById(id);
        if(produtoExistente.isEmpty()){
            throw new ProdutoNaoEncontrado("Produto não encontrado no sistema", id);
        }
        produtoAtualizado.setId(produtoExistente.get().getId());
        return produtoRepository.save(produtoAtualizado);
    }

    public void deletarProduto(Long id){
        Optional<Produto> produtoExistente = produtoRepository.findById(id);
        if(produtoExistente.isEmpty()){
            throw new ProdutoNaoEncontrado("Produto não encontrado no sistema", id);
        }
        produtoRepository.deleteById(id);
    }
}
