package com.gustavo_case.crud.Service;

import java.time.Year;


import com.gustavo_case.crud.Entitys.Autor;
import com.gustavo_case.crud.Entitys.Categoria;
import com.gustavo_case.crud.Entitys.Livro;
import com.gustavo_case.crud.Repository.AutorRepository;
import com.gustavo_case.crud.Repository.CategoriaRepository;
import com.gustavo_case.crud.Repository.LivrosRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;
@Getter
@Setter
@RequiredArgsConstructor
@Service

public class LivroService {

    private final LivrosRepository livrosRepository;
    private final AutorRepository autorRepository;
    private final CategoriaRepository categoriaRepository;

    public Livro criarLivro(Livro livro) {
        if (livro.getTitulo() == null) {
            throw new RuntimeException("Titulo não pode ser vazio");
        }
        if (livro.getIsbn().length() != 13 && livro.getIsbn().length() != 10) {
            throw new RuntimeException("O ISBN deve ter 11 ou 13 caracteres");
        }
        if (livro.getPreco().intValue() < 0) {
            throw new RuntimeException("Preço deve ser positivo  ");
        }

        if (livro.getAnoPublicacao() > Year.now().getValue()) {
            throw new RuntimeException("Ano de publicação não pode ser futuro  ");
        }
        if (livrosRepository.existsByIsbn(livro.getIsbn())){
            throw new IllegalArgumentException("Já existe um livro com esse ISBN: " + livro.getIsbn());

        }


        Autor autor = autorRepository.findById(livro.getAutor().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Autor não encontrado"));
        Categoria categoria = categoriaRepository.findById(livro.getCategoria().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria não encontrada"));

        livro.setAutor(autor);
        livro.setCategoria(categoria);

        return livrosRepository.save(livro);
    }

    public Optional<Livro> buscarPorId(Long id) {
        return livrosRepository.findById(id);
    }

    public void deletarLivro(Long id) {
        livrosRepository.deleteById(id);
    }

    public List<Livro> buscarLivrosPorTitulo(String titulo) {
        return livrosRepository.findByTituloContainingIgnoreCase(titulo);
    }

    public Livro atualizarLivro(Long id, Livro livroAtualizado) {
        Livro livro = livrosRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Livro não encontrado"));

        livro.setTitulo(livroAtualizado.getTitulo());
        livro.setIsbn(livroAtualizado.getIsbn());
        livro.setAnoPublicacao(livroAtualizado.getAnoPublicacao());
        livro.setPreco(livroAtualizado.getPreco());

        if (livroAtualizado.getAutor() != null) {
            Autor autor = autorRepository.findById(livroAtualizado.getAutor().getId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Autor não encontrado"));
            livro.setAutor(autor);
        }

        if (livroAtualizado.getCategoria() != null) {
            Categoria categoria = categoriaRepository.findById(livroAtualizado.getCategoria().getId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria não encontrada"));
            livro.setCategoria(categoria);
        }

        return livrosRepository.save(livro);
    }

    public List<Livro> listarLivros(Long categoriaId, Integer ano, Long autorId) {
        List<Livro> livros = livrosRepository.findAll();

        if (categoriaId != null) {
            livros = livros.stream()
                    .filter(l -> l.getCategoria() != null && l.getCategoria().getId().equals(categoriaId))
                    .toList();
        }

        if (ano != null) {
            livros = livros.stream()
                    .filter(l -> l.getAnoPublicacao() != null && l.getAnoPublicacao().equals(ano))
                    .toList();
        }

        if (autorId != null) {
            livros = livros.stream()
                    .filter(l -> l.getAutor() != null && l.getAutor().getId().equals(autorId))
                    .toList();
        }

        return livros;
    }
}
