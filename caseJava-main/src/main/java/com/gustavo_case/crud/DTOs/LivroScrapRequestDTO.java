package com.gustavo_case.crud.DTOs;

import java.math.BigDecimal;

public record LivroScrapRequestDTO(
        String url,
        Long autorId,
        Long categoriaId
) {}
