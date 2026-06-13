package com.example.seguros.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.seguros.Model.Administrador;
import com.example.seguros.Model.Cliente;
import com.example.seguros.Repository.AdministradorRepository;
import com.example.seguros.Repository.ClienteRepository;

import java.util.List;

@Service
public class AdministradorService {

    @Autowired
    private AdministradorRepository administradorRepository;

    @Autowired
    private ClienteRepository clienteRepository;

   
    public List<Administrador> obtenerTodos() {
        return administradorRepository.findAll();
    }

    
    public Administrador registrarAdministrador(Administrador admin) {
        if (administradorRepository.findByEmail(admin.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Ya existe un administrador con ese email.");
        }
        return administradorRepository.save(admin);
    }

    
    public Cliente registrarCliente(Cliente nuevoCliente) {
        if (clienteRepository.findByDniCuit(nuevoCliente.getDniCuit()).isPresent()) {
            throw new IllegalArgumentException("El cliente ya existe en el sistema.");
        }
        return clienteRepository.save(nuevoCliente);
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