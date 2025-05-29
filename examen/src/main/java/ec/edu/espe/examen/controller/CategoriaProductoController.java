package ec.edu.espe.examen.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ec.edu.espe.examen.modelo.CategoriaProducto;
import ec.edu.espe.examen.servicio.CategoriaProductoService;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaProductoController {

    private final CategoriaProductoService service;

    public CategoriaProductoController(CategoriaProductoService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaProducto> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<CategoriaProducto>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody CategoriaProducto categoriaProducto) {
        service.create(categoriaProducto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Integer id, @RequestBody CategoriaProducto categoriaProducto) {
        categoriaProducto.setId(id);
        service.update(categoriaProducto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
} 