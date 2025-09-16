package com.gustavo_case.crud.Repository;
import com.gustavo_case.crud.Entitys.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor,Long> {
    Optional<Autor> findByNome(String nome);

}
