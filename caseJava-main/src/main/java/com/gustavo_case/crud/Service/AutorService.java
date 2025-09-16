package com.gustavo_case.crud.Service;

import com.gustavo_case.crud.Entitys.Autor;
import com.gustavo_case.crud.Entitys.Livro;
import com.gustavo_case.crud.Repository.AutorRepository;
import com.gustavo_case.crud.Repository.LivrosRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AutorService {

    private final LivrosRepository livrosRepository;
    private final AutorRepository autorRepository;

    public Page<Autor> listarAutores(Pageable pageable) {
        return autorRepository.findAll(pageable);
    }

    public Autor criarAutor(Autor autor){
        if (autor.getEmail().contains("@")==false){
            throw new RuntimeException(" Email do autor deve ter formato válido");
        }
        return autorRepository.save(autor);
    }

    public Optional<Autor> buscarPorId(Long id){
        return autorRepository.findById(id);
    }

    public void deletarAutor(Long id){
        autorRepository.deleteById(id);
    }

    public Autor atualizarAutor(Long id, Autor autorAtualizado) {
        Autor autorExistente = autorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Autor com ID " + id + " não encontrado"));

        if (autorAtualizado.getNome() != null) {
            autorExistente.setNome(autorAtualizado.getNome());
        }
        if (autorAtualizado.getEmail() != null) {
            autorExistente.setEmail(autorAtualizado.getEmail());
        }
        if (autorAtualizado.getDataNascimento() != null) {
            autorExistente.setDataNascimento(autorAtualizado.getDataNascimento());
        }

        return autorRepository.save(autorExistente);
    }


    public List<Livro> listarLivrosDoAutor(Long autorId) {
        Autor autor = autorRepository.findById(autorId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Autor não encontrado"));

        return autor.getLivros();
    }
}
