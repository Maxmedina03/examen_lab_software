package com.example.seguros.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "polizas_automotor") 
@PrimaryKeyJoinColumn(name = "id_poliza")
public class PolizaAutomotor extends Poliza {
    
    @Column(nullable = false, unique = true)
    private String patente;
    @Column (nullable = false)
    private String marca;
    @Column(nullable = false)
    private String modelo;
    @Column(nullable = false)
    private int anio;

    public PolizaAutomotor(){
        super();
        this.setTipoPoliza(TipoPoliza.AUTOMOTRIZ);
    }

    @Override
    public void calcularPrima() {
        
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    
}
