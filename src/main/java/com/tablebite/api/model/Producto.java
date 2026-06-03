package com.tablebite.api.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String descripcion;
    private Double precio;
    private String categoria;
    
   @Column(name = "imagen_url")
    private String imagenUrl;
    private Boolean disponible;
}