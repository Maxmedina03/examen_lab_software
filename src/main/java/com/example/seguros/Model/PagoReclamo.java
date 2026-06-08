package com.example.seguros.Model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "pagos_reclamo")
public class PagoReclamo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pago")
    private Long idPago;
    @Column(name = "fecha_pago", nullable = false)
    private LocalDate fechaPago;
    @Column(name = "monto_total_pagado", nullable = false)
    private BigDecimal monto;
    @ManyToOne
    @JoinColumn(name = "id_reclamo", nullable = false)
    private Reclamo reclamo;
    @OneToMany(mappedBy = "pagoReclamo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PagoMetodoDetalle> PagoMetodoDetalle;

    public PagoReclamo() {}

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

    public Reclamo getReclamo() {
        return reclamo;
    }

    public void setReclamo(Reclamo reclamo) {
        this.reclamo = reclamo;
    }

    public List<PagoMetodoDetalle> getPagosMetodoDetalle() {
        return PagoMetodoDetalle;
    }

    public void setPagosMetodoDetalle(List<PagoMetodoDetalle> pagosMetodoDetalle) {
        this.PagoMetodoDetalle = pagosMetodoDetalle;
    }

    
}
