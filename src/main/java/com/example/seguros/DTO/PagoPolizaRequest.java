package com.example.seguros.DTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.example.seguros.Model.PagoMetodoDetalle;

public class PagoPolizaRequest implements java.io.Serializable {
    private Long idPago;
    private LocalDate fechaPago;
    private BigDecimal monto;
    private Long idPoliza;
    
    private List<PagoMetodoDetalle> pagosMetodoDetalle;
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
    public List<PagoMetodoDetalle> getPagosMetodoDetalle() {
        return pagosMetodoDetalle;
    }
    public void setPagosMetodoDetalle(List<PagoMetodoDetalle> pagosMetodoDetalle) {
        this.pagosMetodoDetalle = pagosMetodoDetalle;
    }

    

}
