package com.gustavo_case.crud.Repository;


import com.gustavo_case.crud.Entitys.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LivrosRepository extends JpaRepository<Livro, Long> {
    List<Livro> findByTituloContainingIgnoreCase(String titulo);
    boolean existsByIsbn(String isbn);
}
