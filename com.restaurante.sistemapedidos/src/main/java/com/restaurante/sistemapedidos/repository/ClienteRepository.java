package com.restaurante.sistemapedidos.repository;

import com.restaurante.sistemapedidos.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {}
