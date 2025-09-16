package com.gustavo_case.crud.Mapper;
//ainda sem uso correto

import com.gustavo_case.crud.DTOs.LivroScrapRequestDTO;
import com.gustavo_case.crud.DTOs.LivroScrapResponseDTO;
import com.gustavo_case.crud.Entitys.Livro;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
@Mapper(componentModel = "spring")
public interface LivroScrapMapper {

    // Livro -> ResponseDTO
    @Mapping(source = "autor.nome", target = "autorNome")
    @Mapping(source = "categoria.nome", target = "categoriaNome")
    LivroScrapResponseDTO toResponseDTO(Livro livro);

    // RequestDTO -> Livro
    @Mapping(target = "autor", ignore = true)
    @Mapping(target = "categoria", ignore = true)
    Livro toEntity(LivroScrapRequestDTO dto);
}
