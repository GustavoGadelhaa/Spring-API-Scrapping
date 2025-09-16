package com.gustavo_case.crud.Repository;

import com.gustavo_case.crud.Entitys.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria,Long> {
    Optional<Categoria> findByNome(String nome);




}
