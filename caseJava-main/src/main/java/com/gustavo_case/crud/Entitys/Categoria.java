package com.gustavo_case.crud.Entitys;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "categoria")
@Entity

public class Categoria {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nome", unique = true)
    private String nome;

    @Column(name = "descricao")
    private String descricao;
}
