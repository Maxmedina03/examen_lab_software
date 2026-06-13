package com.example.seguros.DTO;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.example.seguros.Model.MetodoPago;


public class PagoPolizaRequest implements java.io.Serializable {
    private Long idPago;
    private LocalDate fechaPago;
    private BigDecimal monto;
    private Long idPoliza;
    
    private MetodoPago metodoPago; 
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
    public Long getIdPoliza() {
        return idPoliza;
    }
    public void setIdPoliza(Long idPoliza) {
        this.idPoliza = idPoliza;
    }
    public MetodoPago getMetodoPago() {
        return metodoPago;
    }
    public void setMetodoPago(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }
    
    

}
