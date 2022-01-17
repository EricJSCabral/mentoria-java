package br.com.mentoria.mentoriajava.service;

import br.com.mentoria.mentoriajava.model.Categoria;
import br.com.mentoria.mentoriajava.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria salvarCategoria(Categoria categoria){
        return categoriaRepository.save(categoria);
    }

    public List<Categoria> buscarCategorias(){
        return categoriaRepository.findAll();
    }

    public Categoria buscarUmaCategoria(Long id){
        Categoria categoria = categoriaRepository.findById(id).get();
        if(categoria == null){
            throw new EntityNotFoundException("Categoria não existente");
        }
        return categoriaRepository.findById(id).get();
    }


    public Categoria atualizarCategoria(Long id, Categoria categoriaAtualizada){
        Optional<Categoria> categoriaExistente = categoriaRepository.findById(id);
        if(categoriaExistente.isEmpty()){
            throw new EntityNotFoundException("Categoria não existente");
        }
        categoriaAtualizada.setId(categoriaExistente.get().getId());
        return categoriaRepository.save(categoriaAtualizada);
    }

    public void deletarCategoria(Long id){
        Optional<Categoria> categoriaExistente = categoriaRepository.findById(id);
        if(categoriaExistente.isEmpty()){
            throw new EntityNotFoundException("Categoria não existente");
        }
        categoriaRepository.deleteById(id);
    }
}
