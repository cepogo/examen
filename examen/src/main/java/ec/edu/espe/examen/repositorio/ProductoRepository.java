package ec.edu.espe.examen.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ec.edu.espe.examen.modelo.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
} 