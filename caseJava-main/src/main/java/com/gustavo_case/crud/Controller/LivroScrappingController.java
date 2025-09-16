package com.gustavo_case.crud.Controller;

import com.gustavo_case.crud.DTOs.LivroScrapRequestDTO;
import com.gustavo_case.crud.Entitys.Livro;
import com.gustavo_case.crud.Service.LivroScrappingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/livros")
@RequiredArgsConstructor
public class LivroScrappingController {

    private final LivroScrappingService livroScrappingService;

    @PostMapping("/scrap")
    public ResponseEntity<Livro> inserirLivro(@RequestBody LivroScrapRequestDTO dto) throws IOException {
        Livro livro = livroScrappingService.importarLivro(
                dto.url(),
                dto.autorId(),
                dto.categoriaId()
        );
        return ResponseEntity.ok(livro);
    }


}
