package com.gustavo_case.crud.Service;

import com.gustavo_case.crud.Entitys.Categoria;
import com.gustavo_case.crud.Repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    // GET /api/categorias - Listar todas
    public List<Categoria> listarCategorias() {
        return categoriaRepository.findAll();
    }

    // POST /api/categorias - Criar categoria
    public Categoria criarCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    // GET /api/categorias/{id}/livros - Livros da categoria
    public Optional<Categoria> listarCategoriaPorId(Long id) {
        return categoriaRepository.findById(id);
    }

}
