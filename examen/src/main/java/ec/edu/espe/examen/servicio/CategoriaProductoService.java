package ec.edu.espe.examen.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ec.edu.espe.examen.execption.CategoriaProductoNotFoundException;
import ec.edu.espe.examen.execption.EntityCreateException;
import ec.edu.espe.examen.execption.EntityUpdateException;
import ec.edu.espe.examen.execption.EntityDeleteException;
import ec.edu.espe.examen.modelo.CategoriaProducto;
import ec.edu.espe.examen.repositorio.CategoriaProductoRepository;

@Service
public class CategoriaProductoService {

    private final CategoriaProductoRepository repository;

    public CategoriaProductoService(CategoriaProductoRepository repository) {
        this.repository = repository;
    }

    public CategoriaProducto findById(Integer id) {
        Optional<CategoriaProducto> categoriaOptional = this.repository.findById(id);
        if (categoriaOptional.isPresent()) {
            return categoriaOptional.get();
        } else {
            throw new CategoriaProductoNotFoundException("CategoriaProducto", "El id: " + id + " no corresponde a ningún registro");
        }
    }

    public List<CategoriaProducto> findAll() {
        List<CategoriaProducto> list = this.repository.findAll();
        if (!list.isEmpty()) {
            return list;
        } else {
            throw new CategoriaProductoNotFoundException("CategoriaProducto", "No existe ninguna categoría registrada");
        }
    }

    @Transactional
    public void create(CategoriaProducto categoriaProducto) {
        try {
            this.repository.save(categoriaProducto);
        } catch (RuntimeException rte) {
            throw new EntityCreateException("CategoriaProducto", "Error al crear la categoría. Texto del error: " + rte.getMessage());
        }
    }

    @Transactional
    public void update(CategoriaProducto categoriaProducto) {
        try {
            Optional<CategoriaProducto> categoriaOpt = this.repository.findById(categoriaProducto.getId());
            if (categoriaOpt.isPresent()) {
                CategoriaProducto categoriaDb = categoriaOpt.get();
                categoriaDb.setNombreCategoria(categoriaProducto.getNombreCategoria());
                categoriaDb.setDescripcion(categoriaProducto.getDescripcion());
                this.repository.save(categoriaDb);
            } else {
                throw new CategoriaProductoNotFoundException("CategoriaProducto", "Error al actualizar la categoría.");
            }
        } catch (RuntimeException rte) {
            throw new EntityUpdateException("CategoriaProducto", "Error al actualizar la categoría. Texto del error: " + rte.getMessage());
        }
    }

    @Transactional
    public void delete(Integer id) {
        try {
            Optional<CategoriaProducto> categoriaOpt = this.repository.findById(id);
            if (categoriaOpt.isPresent()) {
                this.repository.deleteById(id);
            } else {
                throw new CategoriaProductoNotFoundException("CategoriaProducto", "No se encontró la categoría con ID: " + id);
            }
        } catch (RuntimeException rte) {
            throw new EntityDeleteException("CategoriaProducto", "Error al eliminar la categoría. Texto del error: " + rte.getMessage());
        }
    }
}