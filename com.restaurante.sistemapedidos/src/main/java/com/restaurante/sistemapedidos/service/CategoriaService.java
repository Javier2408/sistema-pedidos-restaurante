
package com.restaurante.sistemapedidos.service;

import com.restaurante.sistemapedidos.entity.Categoria;
import com.restaurante.sistemapedidos.repository.CategoriaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;

@Service
public class CategoriaService {

    private final CategoriaRepository repository;

    public CategoriaService(CategoriaRepository repository) {
        this.repository = repository;
    }

    public List<Categoria> listar() {
        return repository.findAll();
    }

    public Categoria buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Categoria no encontrada"));
    }

    public Categoria guardar(Categoria categoria) {
        categoria.setId(null);
        return repository.save(categoria);
    }

    public Categoria actualizar(Long id, Categoria datos) {
        Categoria categoria = buscarPorId(id);
        categoria.setNombre(datos.getNombre());
        categoria.setDescripcion(datos.getDescripcion());
        return repository.save(categoria);
    }

    public void eliminar(Long id) {
        Categoria categoria = buscarPorId(id);
        repository.delete(categoria);
    }
}
