
package com.restaurante.sistemapedidos.controller;

import com.restaurante.sistemapedidos.entity.Categoria;
import com.restaurante.sistemapedidos.service.CategoriaService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    private final CategoriaService service;

    public CategoriaController(CategoriaService service) {
        this.service = service;
    }

    @GetMapping
    public List<Categoria> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Categoria buscar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Categoria crear(@RequestBody Categoria categoria) {
        return service.guardar(categoria);
    }

    @PutMapping("/{id}")
    public Categoria actualizar(
            @PathVariable Long id,
            @RequestBody Categoria categoria) {
        return service.actualizar(id, categoria);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}
