package com.restaurante.sistemapedidos.controller;

import com.restaurante.sistemapedidos.entity.Plato;
import com.restaurante.sistemapedidos.repository.PlatoRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@RestController
@RequestMapping("/api/platos")
public class PlatoController {
    private final PlatoRepository repository;

    public PlatoController(PlatoRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Plato> listar() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Plato buscar(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() ->
            new ResponseStatusException(HttpStatus.NOT_FOUND, "No encontrado"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Plato crear(@RequestBody Plato datos) {
        datos.setId(null);
        return repository.save(datos);
    }

    @PutMapping("/{id}")
    public Plato actualizar(@PathVariable Long id, @RequestBody Plato datos) {
        buscar(id);
        datos.setId(id);
        return repository.save(datos);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        repository.delete(buscar(id));
    }
}
