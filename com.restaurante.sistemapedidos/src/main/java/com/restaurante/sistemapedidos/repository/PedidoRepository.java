package com.restaurante.sistemapedidos.repository;

import com.restaurante.sistemapedidos.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}
