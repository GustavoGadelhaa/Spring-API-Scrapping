package com.gustavo_case.crud.Controller;

import com.gustavo_case.crud.Entitys.Categoria;
import com.gustavo_case.crud.Service.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/categoria")
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoriaService categoriaService;

    //GET /api/categorias - Listar todas
    //● POST /api/categorias - Criar categoria
    //● GET /api/categorias/{id}/livros - Livros da categoria

    //GET /api/categorias - Listar todas
    @GetMapping
    public ResponseEntity<List<Categoria>> listarCategorias(){
        return ResponseEntity.ok().body(categoriaService.listarCategorias());
    }
    //● POST /api/categorias - Criar categoria
    @PostMapping
    public ResponseEntity<Categoria> criarCategoria(@RequestBody Categoria categoria){
        return ResponseEntity.ok().body(categoriaService.criarCategoria(categoria));
    }
    //● GET /api/categorias/{id}/livros - Livros da categoria
    @GetMapping("/{id}")
    public ResponseEntity<Categoria> buscarCategoriaPorId(@PathVariable Long id) {
        Categoria categoria = categoriaService.listarCategoriaPorId(id)
                .orElseThrow(() -> new RuntimeException("ID não encontrado"));
        return ResponseEntity.ok(categoria);
    }



}
