package com.example.seguros.Model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "pagos_poliza")
public class PagoPoliza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pago")
    private Long idPago;
    @Column(name = "fecha_pago", nullable = false)
    private LocalDate fechaPago;
    @Column(name = "monto_total_pagado", nullable = false)
    private BigDecimal monto;
    @ManyToOne
    @JoinColumn(name = "id_poliza", nullable = false) // Ahora apunta a la póliza
    @JsonIgnoreProperties({"reclamos", "pagosPoliza"}) 
    private Poliza poliza;
    @OneToMany(mappedBy = "pagoPoliza", cascade = CascadeType.ALL)
    private List<PagoMetodoDetalle> pagosMetodoDetalle;

    public PagoPoliza() {}

    
    public Long getIdPago() {
        return idPago;
    }

    public void setIdPago(Long idPago) {
        this.idPago = idPago;
    }

    public LocalDate getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(LocalDate fechaPago) {
        this.fechaPago = fechaPago;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public Poliza getPoliza() {
        return poliza;
    }

    public void setPoliza(Poliza poliza) {
        this.poliza = poliza;
    }

    public List<PagoMetodoDetalle> getPagosMetodoDetalle() {
        return pagosMetodoDetalle;
    }

    public void setPagosMetodoDetalle(List<PagoMetodoDetalle> pagosMetodoDetalle) {
        this.pagosMetodoDetalle = pagosMetodoDetalle;
    }
}
