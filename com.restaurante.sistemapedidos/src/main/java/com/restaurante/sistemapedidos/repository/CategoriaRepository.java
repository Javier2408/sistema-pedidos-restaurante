
package com.restaurante.sistemapedidos.repository;

import com.restaurante.sistemapedidos.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository
        extends JpaRepository<Categoria, Long> {
}
