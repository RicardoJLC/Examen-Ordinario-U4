package mx.edu.ExamenOrdinarioU4.Controller;

import mx.edu.ExamenOrdinarioU4.Dto.PrestamoDTO;
import mx.edu.ExamenOrdinarioU4.Model.Equipo;
import mx.edu.ExamenOrdinarioU4.Model.Prestamo;
import mx.edu.ExamenOrdinarioU4.Service.PrestamoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prestamos")
@CrossOrigin(origins = "*")
public class prestamoController {

    private final PrestamoService prestamoService;

    public prestamoController(PrestamoService prestamoService) {
        this.prestamoService = prestamoService;
    }

    /**
     * POST /api/prestamos
     * Solicitar un nuevo préstamo
     */
    @PostMapping
    public ResponseEntity<Prestamo> solicitarPrestamo(@RequestBody PrestamoDTO prestamoDTO) {
        try {
            Prestamo prestamo = prestamoService.solicitarPrestamo(prestamoDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(prestamo);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    /**
     * PUT /api/prestamos/{id}/aprobar
     * Aprobar un préstamo (cambiar estado a APROBADO)
     */
    @PutMapping("/{id}/aprobar")
    public ResponseEntity<Prestamo> aprobarPrestamo(@PathVariable Long id) {
        try {
            Prestamo prestamo = prestamoService.aprobarPrestamo(id);
            return ResponseEntity.ok(prestamo);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    /**
     * PUT /api/prestamos/{id}/rechazar
     * Rechazar un préstamo (cambiar estado a RECHAZADO)
     */
    @PutMapping("/{id}/rechazar")
    public ResponseEntity<Prestamo> rechazarPrestamo(@PathVariable Long id) {
        try {
            Prestamo prestamo = prestamoService.rechazarPrestamo(id);
            return ResponseEntity.ok(prestamo);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    /**
     * PUT /api/prestamos/{id}/devolver
     * Registrar devolución del equipo (cambiar estado a DEVUELTO)
     */
    @PutMapping("/{id}/devolver")
    public ResponseEntity<Prestamo> registrarDevolucion(@PathVariable Long id) {
        try {
            Prestamo prestamo = prestamoService.registrarDevolucion(id);
            return ResponseEntity.ok(prestamo);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    /**
     * GET /api/prestamos/equipos/disponibles?nombre=xxx
     * Buscar equipos disponibles por nombre
     */
    @GetMapping("/equipos/disponibles")
    public ResponseEntity<List<Equipo>> buscarEquipoDisponiblePorNombre(
            @RequestParam String nombre) {
        try {
            List<Equipo> equipos = prestamoService.buscarEquipoDisponiblePorNombre(nombre);
            return ResponseEntity.ok(equipos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
