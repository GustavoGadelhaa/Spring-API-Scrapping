package com.gustavo_case.crud.Controller;

import com.gustavo_case.crud.DTOs.LivroScrapRequestDTO;
import com.gustavo_case.crud.DTOs.LivroScrapResponseDTO;
import com.gustavo_case.crud.Entitys.Livro;
import com.gustavo_case.crud.Mapper.LivroScrapMapper;
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
    private final LivroScrapMapper mapper;

    @PostMapping("/scrap")
    public ResponseEntity<LivroScrapResponseDTO> inserirLivro(@RequestBody LivroScrapRequestDTO dto) throws IOException {
        LivroScrapResponseDTO responseDTO = livroScrappingService.importarLivro(dto);
        return ResponseEntity.ok(responseDTO);
    }





}
