package ec.edu.espe.examen.modelo;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto", nullable = false)
    private Integer id;

    @Column(name = "nombre_producto", length = 225, nullable = false)
    private String nombre_producto;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @Column(name = "precio_venta", precision = 10, scale = 2)
    private BigDecimal precio_venta;

    @Column(name = "precio_compra", precision = 10, scale = 2, nullable = false)
    private BigDecimal precio_compra;

    @Column(name = "stock_minimo", nullable = false)
    private Integer stock_actuak;

    @Column(name = "estado_producto", length = 20, nullable = false)
    private String estado_producto;

    @ManyToOne
    @JoinColumn(name = "id_categoria", referencedColumnName = "id_categoria", nullable = false)
    private CategoriaProducto categoriaProducto;

    public Producto() {
    }

    public Producto(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre_producto() {
        return nombre_producto;
    }

    public void setNombre_producto(String nombre_producto) {
        this.nombre_producto = nombre_producto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getPrecio_venta() {
        return precio_venta;
    }

    public void setPrecio_venta(BigDecimal precio_venta) {
        this.precio_venta = precio_venta;
    }

    public BigDecimal getPrecio_compra() {
        return precio_compra;
    }

    public void setPrecio_compra(BigDecimal precio_compra) {
        this.precio_compra = precio_compra;
    }

    public Integer getStock_actuak() {
        return stock_actuak;
    }

    public void setStock_actuak(Integer stock_actuak) {
        this.stock_actuak = stock_actuak;
    }

    public String getEstado_producto() {
        return estado_producto;
    }

    public void setEstado_producto(String estado_producto) {
        this.estado_producto = estado_producto;
    }
    
    public CategoriaProducto getCategoriaProducto() {
        return categoriaProducto;
    }

    public void setCategoriaProducto(CategoriaProducto categoriaProducto) {
        this.categoriaProducto = categoriaProducto;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Producto other = (Producto) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Producto [id=" + id + ", nombre_producto=" + nombre_producto + ", descripcion=" + descripcion
                + ", precio_venta=" + precio_venta + ", precio_compra=" + precio_compra + ", stock_actuak="
                + stock_actuak + ", estado_producto=" + estado_producto + "]";
    }

}
