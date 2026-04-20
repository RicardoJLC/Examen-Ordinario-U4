package mx.edu.ExamenOrdinarioU4.Repository;


import mx.edu.ExamenOrdinarioU4.Model.EstadoPrestamo;
import mx.edu.ExamenOrdinarioU4.Model.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {

    long countByUsuarioIdAndEstadoIn(Long usuarioId, List<EstadoPrestamo> estados);
}