package com.example.seguros.Model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "reclamos")
public class Reclamo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reclamo")
    private Long idReclamo;
    @Column(name = "fecha_siniestro", nullable = false)
    private LocalDate fechaSiniestro;
    @Column(name = "fecha_reclamo", nullable = false)
    private LocalDate fechaReclamo;
    @Column(name = "descripcion_hechos", columnDefinition = "TEXT")
    private String descripcionHechos;
    @Column(name = "monto_reclamado", nullable = false)
    private double montoReclamado;
    @Enumerated(EnumType.STRING)
    @Column(name = "estado_reclamo", nullable = false)
    private EstadoReclamo estadoReclamo;
    @ManyToOne
    @JoinColumn(name = "id_poliza", nullable = false    )
    private Poliza poliza;
    @OneToMany(mappedBy = "reclamo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PagoReclamo> pagosReclamos;

    public Reclamo() {}

    public Long getIdReclamo() {
        return idReclamo;
    }

    public void setIdReclamo(Long idReclamo) {
        this.idReclamo = idReclamo;
    }

    public LocalDate getFechaSiniestro() {
        return fechaSiniestro;
    }

    public void setFechaSiniestro(LocalDate fechaSiniestro) {
        this.fechaSiniestro = fechaSiniestro;
    }

    public LocalDate getFechaReclamo() {
        return fechaReclamo;
    }

    public void setFechaReclamo(LocalDate fechaReclamo) {
        this.fechaReclamo = fechaReclamo;
    }

    public String getDescripcionHechos() {
        return descripcionHechos;
    }

    public void setDescripcionHechos(String descripcionHechos) {
        this.descripcionHechos = descripcionHechos;
    }

    public EstadoReclamo getEstadoReclamo() {
        return estadoReclamo;
    }

    public void setEstadoReclamo(EstadoReclamo estadoReclamo) {
        this.estadoReclamo = estadoReclamo;
    }

    public Poliza getPoliza() {
        return poliza;
    }

    public void setPoliza(Poliza poliza) {
        this.poliza = poliza;
    }

    public List<PagoReclamo> getPagosReclamo() {
        return pagosReclamos;
    }

    public void setPagosReclamo(List<PagoReclamo> pagosReclamos) {
        this.pagosReclamos = pagosReclamos;
    }

    public double getMontoReclamado() {
        return montoReclamado;
    }

    public void setMontoReclamado(double montoReclamado) {
        this.montoReclamado = montoReclamado;
    }

    

}
