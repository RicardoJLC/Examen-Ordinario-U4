package mx.edu.ExamenOrdinarioU4.Repository;

import mx.edu.ExamenOrdinarioU4.Model.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EquipoRepository extends JpaRepository<Equipo, Long> {

    List<Equipo> findByNombreContainingIgnoreCaseAndDisponibleTrue(String nombre);
}
