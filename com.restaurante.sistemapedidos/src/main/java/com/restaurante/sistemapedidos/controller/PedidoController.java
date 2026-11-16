package com.restaurante.sistemapedidos.controller;

import com.restaurante.sistemapedidos.entity.*;
import com.restaurante.sistemapedidos.service.PedidoService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {
    private final PedidoService service;

    public PedidoController(PedidoService service) {
        this.service = service;
    }

    public record CrearPedido(Long clienteId) {}
    public record AgregarPlato(Long platoId, Integer cantidad) {}
    public record CambiarEstado(String estado) {}

    @GetMapping
    public List<Pedido> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Pedido buscar(@PathVariable Long id) {
        return service.buscar(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pedido crear(@RequestBody CrearPedido datos) {
        return service.crear(datos.clienteId());
    }

    @PostMapping("/{id}/detalles")
    @ResponseStatus(HttpStatus.CREATED)
    public DetallePedido agregar(@PathVariable Long id,
                                @RequestBody AgregarPlato datos) {
        return service.agregar(id, datos.platoId(), datos.cantidad());
    }

    @GetMapping("/{id}/detalles")
    public List<DetallePedido> detalles(@PathVariable Long id) {
        return service.obtenerDetalles(id);
    }

    @PatchMapping("/{id}/estado")
    public Pedido cambiarEstado(@PathVariable Long id,
                                @RequestBody CambiarEstado datos) {
        return service.cambiarEstado(id, datos.estado());
    }
}
