package com.example.seguros.DTO;

import java.time.LocalDate;

public class ReclamoRequest implements java.io.Serializable{
    private LocalDate fechaSiniestro;
    private LocalDate fechaReclamo;
    private String descripcionHechos;
    private double montoReclamado;
    private Long idPoliza;

    public ReclamoRequest() {}

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

    public double getMontoReclamado() {
        return montoReclamado;
    }

    public void setMontoReclamado(double montoReclamado) {
        this.montoReclamado = montoReclamado;
    }

    public Long getIdPoliza() {
        return idPoliza;
    }

    public void setIdPoliza(Long idPoliza) {
        this.idPoliza = idPoliza;
    }

   
}