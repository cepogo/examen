package ec.edu.espe.examen.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ec.edu.espe.examen.modelo.Producto;
import ec.edu.espe.examen.servicio.ProductoService;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerPorId(@PathVariable Integer id) {
        Producto producto = productoService.findById(id);
        return ResponseEntity.ok(producto);
    }

    @GetMapping
    public ResponseEntity<List<Producto>> obtenerTodos() {
        List<Producto> productos = productoService.findAll();
        return ResponseEntity.ok(productos);
    }

    @PostMapping
    public ResponseEntity<Void> crear(@RequestBody Producto producto) {
        productoService.create(producto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<Void> cambiarEstado(@PathVariable Integer id, @RequestBody Producto producto) {
        producto.setId(id);
        productoService.cambiarEstado(producto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/stock/aumentar")
    public ResponseEntity<Void> aumentarStock(@PathVariable Integer id, @RequestBody Producto producto) {
        producto.setId(id);
        productoService.aumentarStock(producto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/stock/disminuir")
    public ResponseEntity<Void> disminuirStock(@PathVariable Integer id, @RequestBody Producto producto) {
        producto.setId(id);
        productoService.disminuirStock(producto);
        return ResponseEntity.ok().build();
    }
} 