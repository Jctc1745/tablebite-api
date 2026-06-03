package com.tablebite.api.repository;

import com.tablebite.api.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findByUsuarioId(String usuarioId);
    List<Pedido> findByEstado(String estado);
}