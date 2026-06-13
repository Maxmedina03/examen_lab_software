package com.example.seguros.Model;


import jakarta.persistence.*;

@Entity
@Table(name = "administradores")
public class Administrador extends Usuario{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAdmin;

    public Long getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(Long idAdmin) {
        this.idAdmin = idAdmin;
    }
    
}