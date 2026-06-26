package com.tablebite.api.model;

import jakarta.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import java.util.ArrayList;

@Data
@Entity
@Table(name = "pedidos")
@JsonIgnoreProperties(ignoreUnknown = true)  // ← ignora campos extra del JSON
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String usuarioId;
    private String tipo;
    private String estado;
    private String direccion;
    private Double total;
    private Long creadoEn;

    // Items del pedido (se guardan en tabla pedido_items)
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PedidoItem> items = new ArrayList<>();
}