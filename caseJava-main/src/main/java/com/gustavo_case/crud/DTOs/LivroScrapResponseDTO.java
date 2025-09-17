package com.gustavo_case.crud.DTOs;

import java.math.BigDecimal;
import java.util.List;

public record LivroScrapResponseDTO(
        Long id,
        String isbn,
        String titulo,
        String autorNome,
        String categoriaNome,
        BigDecimal preco,
        int anoPublicacao
) {}
