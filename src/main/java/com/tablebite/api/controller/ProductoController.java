package com.tablebite.api.controller;

import com.tablebite.api.model.Producto;
import com.tablebite.api.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "*")
public class ProductoController {

    @Autowired
    private ProductoRepository repo;

    
    @GetMapping
    public List<Producto> listar() {
        return repo.findAll();
    }

    
    @GetMapping("/disponibles")
    public List<Producto> listarDisponibles() {
        return repo.findByDisponibleTrue();
    }

   
    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtener(@PathVariable Long id) {
        return repo.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    
   @PostMapping
    public Producto crear(@RequestBody Producto producto) {
        producto.setId(null); // ← forzar que sea nuevo
         return repo.save(producto);
}

    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizar(
            @PathVariable Long id,
            @RequestBody Producto datos) {
        return repo.findById(id).map(producto -> {
            producto.setNombre(datos.getNombre());
            producto.setDescripcion(datos.getDescripcion());
            producto.setPrecio(datos.getPrecio());
            producto.setCategoria(datos.getCategoria());
            producto.setImagenUrl(datos.getImagenUrl());
            producto.setDisponible(datos.getDisponible());
            return ResponseEntity.ok(repo.save(producto));
        }).orElse(ResponseEntity.notFound().build());
    }

    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}