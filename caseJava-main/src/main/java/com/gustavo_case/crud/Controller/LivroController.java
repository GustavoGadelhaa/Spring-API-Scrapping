package com.gustavo_case.crud.Controller;



import com.gustavo_case.crud.Entitys.Livro;
import com.gustavo_case.crud.Service.LivroService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/livros")
@RestController
@RequiredArgsConstructor
public class LivroController {
    private final LivroService livroService;


    @PostMapping
    public ResponseEntity<Livro> inserirLivro(@RequestBody Livro livro) {
        return ResponseEntity.ok(livroService.criarLivro(livro));
    }

    //● GET /api/livros/{id} - Buscar por ID - incompleto
    @GetMapping("/{id}")
    public ResponseEntity<Livro> buscarPorId(@PathVariable Long id) {
        Livro livro = livroService.buscarPorId(id).orElseThrow(
                () -> new RuntimeException("ID não encontrado")
        );
        return ResponseEntity.ok(livro);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarLivro(@PathVariable Long id) {
        livroService.deletarLivro(id);
        return ResponseEntity.ok().build();
    }

    //● GET /api/livros/search?titulo={titulo} - Buscar por título
    @GetMapping("/search")
    public ResponseEntity<List<Livro>> buscarLivrosPorTitulo(@RequestParam String titulo) {
        List<Livro> livros = livroService.buscarLivrosPorTitulo(titulo);
        return ResponseEntity.ok(livros);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Livro> atualizarLivro(
            @PathVariable Long id,
            @RequestBody Livro livroAtualizado) {

        Livro livro = livroService.atualizarLivro(id, livroAtualizado);
        return ResponseEntity.ok(livro);
    }

    @GetMapping
    public ResponseEntity<List<Livro>> listarLivros(
            @RequestParam(required = false) Long categoria,
            @RequestParam(required = false) Integer ano,
            @RequestParam(required = false) Long autor) {

        List<Livro> livros = livroService.listarLivros(categoria, ano, autor);
        return ResponseEntity.ok(livros);
    }


}
