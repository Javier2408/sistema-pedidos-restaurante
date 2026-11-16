package com.restaurante.sistemapedidos.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
public class DetallePedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Pedido pedido;

    @ManyToOne(optional = false)
    private Plato plato;

    private Integer cantidad;
    private BigDecimal precioUnitario;
    private BigDecimal subtotal;

    public Long getId() { return id; }
    public Pedido getPedido() { return pedido; }
    public void setPedido(Pedido pedido) { this.pedido = pedido; }
    public Plato getPlato() { return plato; }
    public void setPlato(Plato plato) { this.plato = plato; }
    public Integer getCantidad() { return cantidad; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }
    public BigDecimal getPrecioUnitario() { return precioUnitario; }
    public void setPrecioUnitario(BigDecimal precio) { this.precioUnitario = precio; }
    public BigDecimal getSubtotal() { return subtotal; }
    public void setSubtotal(BigDecimal subtotal) { this.subtotal = subtotal; }
}
