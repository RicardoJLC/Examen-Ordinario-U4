package mx.edu.ExamenOrdinarioU4.Model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "prestamo")
public class Prestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fechaSolicitud;
    private LocalDate fechaDevolucion;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "equipo_id", nullable = false)
    private Equipo equipo;

    @Enumerated(EnumType.STRING)
    private EstadoPrestamo estado;

    public Prestamo() {
    }

    public Prestamo(Long id, LocalDate fechaSolicitud, LocalDate fechaDevolucion, Usuario usuario, Equipo equipo, EstadoPrestamo estado) {
        this.id = id;
        this.fechaSolicitud = fechaSolicitud;
        this.fechaDevolucion = fechaDevolucion;
        this.usuario = usuario;
        this.equipo = equipo;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getFechaSolicitud() {
        return fechaSolicitud;
    }

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public EstadoPrestamo getEstado() {
        return estado;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFechaSolicitud(LocalDate fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public void setFechaDevolucion(LocalDate fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public void setEstado(EstadoPrestamo estado) {
        this.estado = estado;
    }
}
