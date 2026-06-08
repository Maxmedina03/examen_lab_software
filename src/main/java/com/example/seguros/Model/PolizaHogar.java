package com.example.seguros.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "polizas_hogar")
@PrimaryKeyJoinColumn(name = "id_poliza")
public class PolizaHogar extends Poliza {
    @Column(name = "direccion_inmueble", nullable = false)
    private String direccionInmueble;
    @Column(name = "metros_cuadrados", nullable = false)
    private int metrosCuadrados;
    @Column(name = "tiene_alarma", nullable = false)
    private boolean TieneAlarma;
    
    public PolizaHogar(){
        super();
        this.setTipoPoliza(TipoPoliza.HOGAR);
    }
    
    @Override
    public void calcularPrima() {

    }

    public String getDireccionInmueble() {
        return direccionInmueble;
    }

    public void setDireccionInmueble(String direccionInmueble) {
        this.direccionInmueble = direccionInmueble;
    }

    public int getMetrosCuadrados() {
        return metrosCuadrados;
    }

    public void setMetrosCuadrados(int metrosCuadrados) {
        this.metrosCuadrados = metrosCuadrados;
    }

    public boolean isTieneAlarma() {
        return TieneAlarma;
    }

    public void setTieneAlarma(boolean tieneAlarma) {
        TieneAlarma = tieneAlarma;
    }

    
}
