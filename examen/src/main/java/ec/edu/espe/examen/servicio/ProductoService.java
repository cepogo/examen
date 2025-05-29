package ec.edu.espe.examen.servicio;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ec.edu.espe.examen.execption.ProductoNotFoundException;
import ec.edu.espe.examen.execption.EntityCreateException;
import ec.edu.espe.examen.execption.EntityUpdateException;
import ec.edu.espe.examen.modelo.Producto;
import ec.edu.espe.examen.repositorio.ProductoRepository;

@Service
public class ProductoService {

    private final ProductoRepository repository;
    private static final BigDecimal PORCENTAJE_GANANCIA = new BigDecimal("0.25");

    public ProductoService(ProductoRepository repository) {
        this.repository = repository;
    }

    public Producto findById(Integer id) {
        Optional<Producto> productoOptional = this.repository.findById(id);
        if (productoOptional.isPresent()) {
            return productoOptional.get();
        } else {
            throw new ProductoNotFoundException("Producto", "El id: " + id + " no corresponde a ningún registro");
        }
    }

    public List<Producto> findAll() {
        List<Producto> list = this.repository.findAll();
        if (!list.isEmpty()) {
            return list;
        } else {
            throw new ProductoNotFoundException("Producto", "No existe ningún producto registrado");
        }
    }

    @Transactional
    public void create(Producto producto) {
        try {
            // Calcular precio de venta inicial
            if (producto.getPrecio_compra() != null) {
                BigDecimal precioVenta = producto.getPrecio_compra().multiply(BigDecimal.ONE.add(PORCENTAJE_GANANCIA));
                producto.setPrecio_venta(precioVenta);
            }
            this.repository.save(producto);
        } catch (RuntimeException rte) {
            throw new EntityCreateException("Producto", "Error al crear el producto. Texto del error: " + rte.getMessage());
        }
    }

         
    @Transactional
    public void cambiarEstado(Producto producto) {
        try {
            Optional<Producto> productoOpt = this.repository.findById(producto.getId());
            if (productoOpt.isPresent()) {
                Producto productoDb = productoOpt.get();
                
                // Validar el nuevo estado
                if (!producto.getEstado_producto().equals("ACTIVO") && 
                    !producto.getEstado_producto().equals("INACTIVO") && 
                    !producto.getEstado_producto().equals("AGOTADO")) {
                    throw new EntityUpdateException("Producto", "Estado no válido. Debe ser: ACTIVO, INACTIVO o AGOTADO");
                }

                // Validar cambio a estado AGOTADO
                if (producto.getEstado_producto().equals("AGOTADO") && productoDb.getStock_actuak() > 0) {
                    throw new EntityUpdateException("Producto", "No se puede cambiar a estado AGOTADO si hay stock disponible");
                }

                // Validar cambio a estado ACTIVO
                if (producto.getEstado_producto().equals("ACTIVO") && productoDb.getStock_actuak() == 0) {
                    throw new EntityUpdateException("Producto", "No se puede cambiar a estado ACTIVO si no hay stock disponible");
                }

                productoDb.setEstado_producto(producto.getEstado_producto());
                this.repository.save(productoDb);
            } else {
                throw new ProductoNotFoundException("Producto", "No se encontró el producto con ID: " + producto.getId());
            }
        } catch (RuntimeException rte) {
            throw new EntityUpdateException("Producto", "Error al cambiar el estado del producto. Texto del error: " + rte.getMessage());
        }
    }

    @Transactional
    public void aumentarStock(Producto producto) {
        try {
            Optional<Producto> productoOpt = this.repository.findById(producto.getId());
            if (productoOpt.isPresent()) {
                Producto productoDb = productoOpt.get();
                
                // Actualizar stock
                Integer nuevoStock = productoDb.getStock_actuak() + producto.getStock_actuak();
                productoDb.setStock_actuak(nuevoStock);
                
                // Actualizar precios
                productoDb.setPrecio_compra(producto.getPrecio_compra());
                BigDecimal precioVenta = producto.getPrecio_compra().multiply(BigDecimal.ONE.add(PORCENTAJE_GANANCIA));
                productoDb.setPrecio_venta(precioVenta);
                
                // Actualizar estado
                productoDb.setEstado_producto("ACTIVO");
                
                this.repository.save(productoDb);
            } else {
                throw new ProductoNotFoundException("Producto", "No se encontró el producto con ID: " + producto.getId());
            }
        } catch (RuntimeException rte) {
            throw new EntityUpdateException("Producto", "Error al aumentar el stock del producto. Texto del error: " + rte.getMessage());
        }
    }

    @Transactional
    public void disminuirStock(Producto producto) {
        try {
            Optional<Producto> productoOpt = this.repository.findById(producto.getId());
            if (productoOpt.isPresent()) {
                Producto productoDb = productoOpt.get();
                
                // Verificar si hay suficiente stock
                if (productoDb.getStock_actuak() >= producto.getStock_actuak()) {
                    Integer nuevoStock = productoDb.getStock_actuak() - producto.getStock_actuak();
                    productoDb.setStock_actuak(nuevoStock);
                    
                    // Actualizar estado si el stock llega a 0
                    if (nuevoStock == 0) {
                        productoDb.setEstado_producto("AGOTADO");
                    }
                    
                    this.repository.save(productoDb);
                } else {
                    throw new EntityUpdateException("Producto", "No hay suficiente stock disponible. Stock actual: " + productoDb.getStock_actuak());
                }
            } else {
                throw new ProductoNotFoundException("Producto", "No se encontró el producto con ID: " + producto.getId());
            }
        } catch (RuntimeException rte) {
            throw new EntityUpdateException("Producto", "Error al disminuir el stock del producto. Texto del error: " + rte.getMessage());
        }
    }
} 