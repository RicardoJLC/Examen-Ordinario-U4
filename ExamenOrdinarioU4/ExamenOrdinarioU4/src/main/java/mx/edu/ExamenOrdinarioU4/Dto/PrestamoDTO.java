package mx.edu.ExamenOrdinarioU4.Dto;

import java.time.LocalDate;

public class PrestamoDTO {

    private Long usuarioId;
    private Long equipoId;
    private LocalDate fechaDevolucion;

    public PrestamoDTO() {
    }

    public PrestamoDTO(Long usuarioId, Long equipoId, LocalDate fechaDevolucion) {
        this.usuarioId = usuarioId;
        this.equipoId = equipoId;
        this.fechaDevolucion = fechaDevolucion;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Long getEquipoId() {
        return equipoId;
    }

    public void setEquipoId(Long equipoId) {
        this.equipoId = equipoId;
    }

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(LocalDate fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }
}