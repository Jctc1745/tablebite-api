package com.tablebite.api.controller;

import com.tablebite.api.model.Pedido;
import com.tablebite.api.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/pedidos")
@CrossOrigin(origins = "*")
public class PedidoController {

    @Autowired
    private PedidoRepository repo;

    // GET todos los pedidos
    @GetMapping
    public List<Pedido> listar() {
        return repo.findAll();
    }

    // GET pedidos por usuario
    @GetMapping("/usuario/{usuarioId}")
    public List<Pedido> porUsuario(@PathVariable String usuarioId) {
        return repo.findByUsuarioId(usuarioId);
    }

    // GET pedidos por estado
    @GetMapping("/estado/{estado}")
    public List<Pedido> porEstado(@PathVariable String estado) {
        return repo.findByEstado(estado);
    }

    // GET por id
    @GetMapping("/{id}")
    public ResponseEntity<Pedido> obtener(@PathVariable Long id) {
        return repo.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    // POST crear pedido
    @PostMapping
    public Pedido crear(@RequestBody Pedido pedido) {
        pedido.setEstado("PENDIENTE");
        pedido.setCreadoEn(System.currentTimeMillis());
        return repo.save(pedido);
    }

    // PUT cambiar estado
    @PutMapping("/{id}/estado")
    public ResponseEntity<Pedido> cambiarEstado(
            @PathVariable Long id,
            @RequestBody Map<String, String> body) {
        return repo.findById(id).map(pedido -> {
            pedido.setEstado(body.get("estado"));
            return ResponseEntity.ok(repo.save(pedido));
        }).orElse(ResponseEntity.notFound().build());
    }

    // DELETE eliminar pedido
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
