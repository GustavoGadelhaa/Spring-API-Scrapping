package com.gustavo_case.crud.Service;

import com.gustavo_case.crud.Entitys.Autor;
import com.gustavo_case.crud.Entitys.Categoria;
import com.gustavo_case.crud.Entitys.Livro;
import com.gustavo_case.crud.Repository.AutorRepository;
import com.gustavo_case.crud.Repository.CategoriaRepository;
import com.gustavo_case.crud.Repository.LivrosRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;

@Slf4j
@RequiredArgsConstructor
@Service
public class LivroScrappingService {

    private final LivrosRepository livrosRepository;
    private final AutorRepository autorRepository;
    private final CategoriaRepository categoriaRepository;

    public Livro importarLivro(String url, Long autorId, Long categoriaId) throws IOException {

        Document pagina = Jsoup.connect(url)
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36")
                .referrer("https://www.google.com")
                .timeout(10_000)
                .get();

        // TÍTULO
        String titulo = pagina.selectFirst("#productTitle") != null
                ? pagina.selectFirst("#productTitle").text().trim()
                : "Título não encontrado";

        // PREÇO (whole + fraction)
        Element precoWholeEl = pagina.selectFirst("div.a-box-inner span.a-price-whole");
        Element precoFractionEl = pagina.selectFirst("div.a-box-inner span.a-price-fraction");

        if (precoWholeEl == null) {
            throw new RuntimeException("Preço não encontrado na página.");
        }

        String whole = precoWholeEl.text().replaceAll("[^0-9]", "");
        String fraction = (precoFractionEl != null) ? precoFractionEl.text().trim() : "00";
        String precoStr = whole + "." + fraction;
        BigDecimal precoCompleto = new BigDecimal(precoStr);

        // ISBN com opcoes reservas
        Element isbnEl = pagina.selectFirst(":nth-child(7) > .a-list-item > span:nth-child(2)");
        if (isbnEl == null) isbnEl = pagina.selectFirst(":nth-child(8) > .a-list-item > span:nth-child(2)");
        if (isbnEl == null) isbnEl = pagina.selectFirst("#rpi-attribute-book_details-isbn10 > .a-section.a-spacing-none.a-text-center.rpi-attribute-value > span");
        if (isbnEl == null) isbnEl = pagina.selectFirst("#rpi-attribute-book_details-isbn13 > .a-section.a-spacing-none.a-text-center.rpi-attribute-value > span");
        String isbn = (isbnEl != null) ? isbnEl.text().trim() : "ISBN não encontrado";

        // ANO DE PUBLICAÇÃO
        Integer anoPublicacao = null;
        try {
            anoPublicacao = Integer.parseInt(pagina.selectFirst(":nth-child(3) > .a-list-item > span:nth-child(2)")
                    .text().split(" ")[2]);
        } catch (Exception e) {
            try {
                anoPublicacao = Integer.parseInt(pagina.selectFirst(":nth-child(2) > .a-list-item > span:nth-child(2)")
                        .text().split(" ")[2]);
            } catch (Exception ignored) {
            }
        }

        // BUSCA AUTOR E CATEGORIA
        Autor autor = autorRepository.findById(autorId)
                .orElseThrow(() -> new RuntimeException("Autor não encontrado"));
        Categoria categoria = categoriaRepository.findById(categoriaId)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        // MONTA ENTIDADE
        Livro livro = new Livro();
        livro.setTitulo(titulo);
        livro.setPreco(precoCompleto);
        livro.setIsbn(isbn);
        livro.setAnoPublicacao(anoPublicacao);
        livro.setAutor(autor);
        livro.setCategoria(categoria);
        livro.setUrl(url);

        // SALVA e retorna
        return livrosRepository.save(livro);
    }
}
