package com.gustavo_case.crud.Controller;


import com.gustavo_case.crud.Entitys.Livro;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

import com.gustavo_case.crud.Entitys.Autor;
import com.gustavo_case.crud.Service.AutorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("/api/autores")
public class AutorController {

    private final AutorService autorService;

    //● GET /api/autores - Listar todos (com paginação)

    @GetMapping
    public ResponseEntity<Page<Autor>> listarAutores(
            @PageableDefault(size = 10, sort = "nome") Pageable pageable) {

        Page<Autor> autores = autorService.listarAutores(pageable);
        return ResponseEntity.ok(autores);
    }

    // Usar CamelCase na REQUEST ("dataNascimento")
    @PostMapping
    public ResponseEntity<Autor> criarAutor(@RequestBody Autor autor) {
        return ResponseEntity.ok().body(autorService.criarAutor(autor));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Autor> buscarPorId(@PathVariable Long id) {
        Autor autor=autorService.buscarPorId(id).orElseThrow(
                ()-> new RuntimeException("id não encontrado"));
        return ResponseEntity.ok(autor);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAutor(@PathVariable Long id) {
        autorService.deletarAutor(id);
        return ResponseEntity.noContent().build();
    }

    // PUT /api/autores/{id} - Atualizar Autor
    @PutMapping("/{id}")
    public ResponseEntity<Autor> atualizarAutor(
            @PathVariable Long id,
            @RequestBody Autor autorAtualizado) {

        Autor autor = autorService.atualizarAutor(id, autorAtualizado);
        return ResponseEntity.ok(autor);
    }

    @GetMapping("/{id}/livros")
    public ResponseEntity<List<Livro>> listarLivrosDoAutor(@PathVariable Long id) {
        List<Livro> livros = autorService.listarLivrosDoAutor(id);
        return ResponseEntity.ok(livros);
    }


}
