package mx.edu.ExamenOrdinarioU4.Repository;

import mx.edu.ExamenOrdinarioU4.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}