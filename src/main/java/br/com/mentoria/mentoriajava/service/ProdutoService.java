package br.com.mentoria.mentoriajava.service;

import br.com.mentoria.mentoriajava.model.Produto;
import br.com.mentoria.mentoriajava.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public Produto salvarProduto(Produto produto){
        return produtoRepository.save(produto);
    }

    public List<Produto> buscarTodosProdutos(){
        return produtoRepository.findAll();
    }

    public Produto buscarUmProduto(Long id){
        Produto produto = produtoRepository.findById(id).get();
        if(produto == null){
            throw new EntityNotFoundException("Produto não existente");
        }
        return produtoRepository.findById(id).get();
    }


    public Produto atualizarProdutos(Long id, Produto produtoAtualizado){
        Optional<Produto> produtoExistente = produtoRepository.findById(id);
        if(produtoExistente.isEmpty()){
            throw new EntityNotFoundException("Categoria não existente");
        }
        produtoAtualizado.setId(produtoExistente.get().getId());
        return produtoRepository.save(produtoAtualizado);
    }

    public void deletarProduto(Long id){
        Optional<Produto> produtoExistente = produtoRepository.findById(id);
        if(produtoExistente.isEmpty()){
            throw new EntityNotFoundException("Produto não existente");
        }
        produtoRepository.deleteById(id);
    }
}
