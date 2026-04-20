package mx.edu.ExamenOrdinarioU4.Service;

import mx.edu.ExamenOrdinarioU4.Dto.PrestamoDTO;
import mx.edu.ExamenOrdinarioU4.Model.Equipo;
import mx.edu.ExamenOrdinarioU4.Model.EstadoPrestamo;
import mx.edu.ExamenOrdinarioU4.Model.Prestamo;
import mx.edu.ExamenOrdinarioU4.Model.Usuario;
import mx.edu.ExamenOrdinarioU4.Repository.EquipoRepository;
import mx.edu.ExamenOrdinarioU4.Repository.PrestamoRepository;
import mx.edu.ExamenOrdinarioU4.Repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PrestamoService {

    private final PrestamoRepository prestamoRepository;
    private final UsuarioRepository usuarioRepository;
    private final EquipoRepository equipoRepository;

    public PrestamoService(PrestamoRepository prestamoRepository,
                           UsuarioRepository usuarioRepository,
                           EquipoRepository equipoRepository) {
        this.prestamoRepository = prestamoRepository;
        this.usuarioRepository = usuarioRepository;
        this.equipoRepository = equipoRepository;
    }

    public Prestamo solicitarPrestamo(PrestamoDTO dto) {
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Equipo equipo = equipoRepository.findById(dto.getEquipoId())
                .orElseThrow(() -> new RuntimeException("Equipo no encontrado"));

        Prestamo prestamo = new Prestamo();
        prestamo.setFechaSolicitud(LocalDate.now());
        prestamo.setFechaDevolucion(dto.getFechaDevolucion());
        prestamo.setUsuario(usuario);
        prestamo.setEquipo(equipo);

        boolean usuarioActivo = usuario.isActivo();
        boolean equipoDisponible = equipo.isDisponible();

        long prestamosActivos = prestamoRepository.countByUsuarioIdAndEstadoIn(
                usuario.getId(),
                List.of(EstadoPrestamo.SOLICITADO, EstadoPrestamo.APROBADO)
        );

        if (!usuarioActivo || !equipoDisponible || prestamosActivos >= 2) {
            prestamo.setEstado(EstadoPrestamo.RECHAZADO);
        } else {
            prestamo.setEstado(EstadoPrestamo.SOLICITADO);
        }

        return prestamoRepository.save(prestamo);
    }

    public Prestamo aprobarPrestamo(Long idPrestamo) {
        Prestamo prestamo = prestamoRepository.findById(idPrestamo)
                .orElseThrow(() -> new RuntimeException("Préstamo no encontrado"));

        if (prestamo.getEstado() != EstadoPrestamo.SOLICITADO) {
            throw new RuntimeException("Solo se pueden aprobar préstamos en estado SOLICITADO");
        }

        prestamo.setEstado(EstadoPrestamo.APROBADO);

        Equipo equipo = prestamo.getEquipo();
        equipo.setDisponible(false);
        equipoRepository.save(equipo);

        return prestamoRepository.save(prestamo);
    }

    public Prestamo registrarDevolucion(Long idPrestamo) {
        Prestamo prestamo = prestamoRepository.findById(idPrestamo)
                .orElseThrow(() -> new RuntimeException("Préstamo no encontrado"));

        prestamo.setEstado(EstadoPrestamo.DEVUELTO);

        Equipo equipo = prestamo.getEquipo();
        equipo.setDisponible(true);
        equipoRepository.save(equipo);

        return prestamoRepository.save(prestamo);
    }

    public Prestamo rechazarPrestamo(Long idPrestamo) {
        Prestamo prestamo = prestamoRepository.findById(idPrestamo)
                .orElseThrow(() -> new RuntimeException("Préstamo no encontrado"));

        if (prestamo.getEstado() != EstadoPrestamo.SOLICITADO) {
            throw new RuntimeException("Solo se puede rechazar un préstamo en estado SOLICITADO");
        }

        prestamo.setEstado(EstadoPrestamo.RECHAZADO);
        return prestamoRepository.save(prestamo);
    }

    public List<Equipo> buscarEquipoDisponiblePorNombre(String nombre) {
        return equipoRepository.findByNombreContainingIgnoreCaseAndDisponibleTrue(nombre);
    }
}