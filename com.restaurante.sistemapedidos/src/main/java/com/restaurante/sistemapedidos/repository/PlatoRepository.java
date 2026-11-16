package com.restaurante.sistemapedidos.repository;

import com.restaurante.sistemapedidos.entity.Plato;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlatoRepository extends JpaRepository<Plato, Long> {}
