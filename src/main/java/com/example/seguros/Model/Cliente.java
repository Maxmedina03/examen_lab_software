package com.example.seguros.Model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "clientes")
public class Cliente extends Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCliente;
    
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval= true)
    private List<Poliza> polizas;

    public Cliente() {
        super();
    }
    
    public Cliente(String nombre, String apellido, String dniCuit, String email) {
        this.setNombre(nombre);
        this.setApellido(apellido);
        this.setDniCuit(dniCuit);
        this.setEmail(email);
    }
    public List<Poliza> getPolizas() {
        return polizas;
    }

    public void setPolizas(List<Poliza> polizas) {
        this.polizas = polizas;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }


}
