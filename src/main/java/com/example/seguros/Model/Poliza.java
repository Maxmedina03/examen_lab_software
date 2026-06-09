package com.example.seguros.Model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "polizas")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Poliza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_poliza")
    private Long idPoliza;
    @Column(name = "numero_poliza", nullable = false, unique = true)
    private String numeroPoliza;
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_poliza", nullable = false)
    private TipoPoliza tipoPoliza;
    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate fechaInicio;
    @Column(name = "fecha_fin", nullable = false)
    private LocalDate fechaFin;
    @Column(nullable = false)
    private BigDecimal prima;
    @Column(name = "cobertura_maxima", nullable = false)
    private BigDecimal coberturaMaxima;
    @Enumerated(EnumType.STRING)
    @Column(name = "estado_poliza", nullable = false)
    private EstadoPoliza estadoPoliza;
    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;
    @OneToMany(mappedBy = "poliza", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Reclamo> reclamos;
    
    public abstract void calcularPrima();

    public Poliza() {}

    public Long getIdPoliza() {
        return idPoliza;
    }

    public void setIdPoliza(Long idPoliza) {
        this.idPoliza = idPoliza;
    }

    public String getNumeroPoliza() {
        return numeroPoliza;
    }

    public void setNumeroPoliza(String numeroPoliza) {
        this.numeroPoliza = numeroPoliza;
    }

    public TipoPoliza getTipoPoliza() {
        return tipoPoliza;
    }

    public void setTipoPoliza(TipoPoliza tipoPoliza) {
        this.tipoPoliza = tipoPoliza;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public BigDecimal getCoberturaMaxima() {
        return coberturaMaxima;
    }

    public void setCoberturaMaxima(BigDecimal coberturaMaxima) {
        this.coberturaMaxima = coberturaMaxima;
    }

    public EstadoPoliza getEstadoPoliza() {
        return estadoPoliza;
    }

    public void setEstadoPoliza(EstadoPoliza estadoPoliza) {
        this.estadoPoliza = estadoPoliza;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Reclamo> getReclamos() {
        return reclamos;
    }

    public void setReclamos(List<Reclamo> reclamos) {
        this.reclamos = reclamos;
    }

    public BigDecimal getPrima() {
        return prima;
    }

    public void setPrima(BigDecimal prima) {
        this.prima = prima;
    }

    
}
