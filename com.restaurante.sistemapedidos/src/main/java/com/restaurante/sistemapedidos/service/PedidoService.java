package com.restaurante.sistemapedidos.service;


import com.restaurante.sistemapedidos.exception.PedidoInvalidoException;
import com.restaurante.sistemapedidos.exception.RecursoNoEncontradoException;
import com.restaurante.sistemapedidos.exception.ReglaNegocioException;
import com.restaurante.sistemapedidos.entity.*;
import com.restaurante.sistemapedidos.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Service
public class PedidoService {
    private final PedidoRepository pedidos;
    private final DetallePedidoRepository detalles;
    private final ClienteRepository clientes;
    private final PlatoRepository platos;

    public PedidoService(PedidoRepository pedidos,
                         DetallePedidoRepository detalles,
                         ClienteRepository clientes,
                         PlatoRepository platos) {
        this.pedidos = pedidos;
        this.detalles = detalles;
        this.clientes = clientes;
        this.platos = platos;
    }

    public List<Pedido> listar() {
        return pedidos.findAll();
    }

   
public Pedido buscar(Long id) {
    return pedidos.findById(id).orElseThrow(() ->
            new RecursoNoEncontradoException(
                    "Pedido no encontrado"));
}


    

    @Transactional
    public Pedido crear(Long clienteId) {
        Cliente cliente = clientes.findById(clienteId).orElseThrow(() ->
            new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado"));
        Pedido pedido = new Pedido();
pedido.setCliente(cliente);
pedido.setEstado("PENDIENTE");
pedido.setTotal(BigDecimal.ZERO);
return pedidos.save(pedido);
    }

    @Transactional
    public DetallePedido agregar(Long pedidoId, Long platoId, Integer cantidad) {
        Pedido pedido = buscar(pedidoId);

        if (!"PENDIENTE".equals(pedido.getEstado())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                "Solo se pueden modificar pedidos pendientes");
        }

        if (cantidad == null || cantidad <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                "La cantidad debe ser mayor que cero");
        }

        Plato plato = platos.findById(platoId).orElseThrow(() ->
            new ResponseStatusException(HttpStatus.NOT_FOUND, "Plato no encontrado"));

        if (!Boolean.TRUE.equals(plato.getDisponible())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                "El plato no esta disponible");
        }

        
if (plato.getPrecio() == null ||
        plato.getPrecio().compareTo(BigDecimal.ZERO) < 0) {
    throw new ReglaNegocioException(
            "El precio del plato no es valido");
}

        

        DetallePedido detalle = new DetallePedido();
        detalle.setPedido(pedido);
        detalle.setPlato(plato);
        detalle.setCantidad(cantidad);
        detalle.setPrecioUnitario(plato.getPrecio());
        detalle.setSubtotal(plato.getPrecio().multiply(BigDecimal.valueOf(cantidad)));

        DetallePedido guardado = detalles.save(detalle);
        pedido.setTotal(pedido.getTotal().add(detalle.getSubtotal()));
        pedidos.save(pedido);
        return guardado;
    }

    public List<DetallePedido> obtenerDetalles(Long pedidoId) {
        buscar(pedidoId);
        return detalles.findByPedidoId(pedidoId);
    }

    @Transactional
    public Pedido cambiarEstado(Long id, String estado) {
        Pedido pedido = buscar(id);
        if (estado == null ||
            !Set.of("PENDIENTE", "PREPARANDO", "ENTREGADO", "CANCELADO").contains(estado)) {
            
throw new PedidoInvalidoException("Estado no valido");

        }
        pedido.setEstado(estado);
        return pedidos.save(pedido);
    }
}
