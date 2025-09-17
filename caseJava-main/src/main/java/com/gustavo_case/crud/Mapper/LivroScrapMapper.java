package com.gustavo_case.crud.Mapper;

import com.gustavo_case.crud.DTOs.LivroScrapRequestDTO;
import com.gustavo_case.crud.DTOs.LivroScrapResponseDTO;
import com.gustavo_case.crud.Entitys.Livro;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LivroScrapMapper {

    // Entity -> ResponseDTO
    @Mapping(source = "autor.nome", target = "autorNome")
    @Mapping(source = "categoria.nome", target = "categoriaNome")
    LivroScrapResponseDTO entityToResponseDto(Livro livro);


    // RequestDTO -> Entity
    Livro requestDtoToEntity(LivroScrapRequestDTO dto);
}
