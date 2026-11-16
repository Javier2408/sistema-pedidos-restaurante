package com.restaurante.sistemapedidos.entity;

import jakarta.persistence.*;

@Entity
public class Plato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private java.math.BigDecimal precio;
    private Boolean disponible = true;
    @jakarta.persistence.ManyToOne
    private Categoria categoria;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public java.math.BigDecimal getPrecio() { return precio; }
    public void setPrecio(java.math.BigDecimal precio) { this.precio = precio; }
    public Boolean getDisponible() { return disponible; }
    public void setDisponible(Boolean disponible) { this.disponible = disponible; }
    public Categoria getCategoria() { return categoria; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }
    
}
