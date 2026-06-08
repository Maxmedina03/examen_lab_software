package com.example.seguros.Model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

    @Entity
    @Table(name = "pago_metodo_detalles")
    public class PagoMetodoDetalle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle")
    private Long idDetalle;

    @Column(name = "monto_parcial", nullable = false)
    private BigDecimal montoParcial;

    @Enumerated(EnumType.STRING)
    @Column(name = "metodo_pago", nullable = false)
    private MetodoPago metodoPago;

    @ManyToOne
    @JoinColumn(name = "id_pago", nullable = false)
    private PagoReclamo pagoReclamo;

    public PagoMetodoDetalle() {}

    public Long getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(Long idDetalle) {
        this.idDetalle = idDetalle;
    }

    public BigDecimal getMontoParcial() {
        return montoParcial;
    }

    public void setMontoParcial(BigDecimal montoParcial) {
        this.montoParcial = montoParcial;
    }

    public MetodoPago getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }

    public PagoReclamo getPagoReclamo() {
        return pagoReclamo;
    }

    public void setPagoReclamo(PagoReclamo pagoReclamo) {
        this.pagoReclamo = pagoReclamo;
    }

   

}

