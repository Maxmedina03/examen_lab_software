package com.example.seguros.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.seguros.Model.Administrador;
import com.example.seguros.Repository.AdministradorRepository;

@Service
public class AdministradorService {

    @Autowired
    private AdministradorRepository administradorRepository;

    public List<Administrador> obtenerTodos() {
        return administradorRepository.findAll();
    }

    public Administrador registrarAdministrador(Administrador admin) {
        if (administradorRepository.findByDniCuit(admin.getDniCuit()).isPresent()) {
            throw new IllegalArgumentException("El administrador ya existe.");
        }
        return administradorRepository.save(admin);
    }

    public boolean verificarCredenciales(String dniCuit, String contraseña) {
        return administradorRepository.findByDniCuit(dniCuit)
                .map(admin -> admin.getContraseña().equals(contraseña))
                .orElse(false);
    }

    public Administrador obtenerAdministradorPorId(Long idAdmin) {
        return administradorRepository.findByIdAdmin(idAdmin)
                .orElseThrow(() -> new IllegalArgumentException("Administrador no encontrado."));
    }

    public Administrador actualizarAdministrador(Long id, Administrador adminActualizado) {
        Administrador adminExistente = obtenerAdministradorPorId(id);

        adminExistente.setNombre(adminActualizado.getNombre());
        adminExistente.setApellido(adminActualizado.getApellido());
        adminExistente.setContraseña(adminActualizado.getContraseña());
        adminExistente.setEmail(adminActualizado.getEmail());
        adminExistente.setDireccion(adminActualizado.getDireccion());
        adminExistente.setTelefono(adminActualizado.getTelefono());

        return administradorRepository.save(adminExistente);
    }

    public void eliminarAdministrador(Long id) {
        Administrador administrador = obtenerAdministradorPorId(id);
        administradorRepository.delete(administrador);
    }
}