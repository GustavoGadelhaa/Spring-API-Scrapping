    package com.gustavo_case.crud.Entitys;

    import com.fasterxml.jackson.annotation.JsonBackReference;
    import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
    import jakarta.persistence.*;
    import lombok.*;

    import java.math.BigDecimal;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Entity

    @Table(name = "livros")
    public class Livro {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;

        @Column(nullable = false)
        private String titulo;

        @Column(unique = true, nullable = false)
        private String isbn;

        @Column(name = "ano_publicacao")
        private Integer anoPublicacao;

        @Column(precision = 10, scale = 2)
        private BigDecimal preco;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "autor_id")
        @JsonBackReference
        private Autor autor;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "categoria_id", nullable = false)
        @JsonIgnoreProperties("livros")
        private Categoria categoria;

        @Column
        private String url;
    }
