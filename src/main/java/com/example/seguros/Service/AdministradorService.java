package com.example.seguros.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.seguros.Model.Administrador;
import com.example.seguros.Model.Cliente;
import com.example.seguros.Repository.AdministradorRepository;
import com.example.seguros.Repository.ClienteRepository;

@Service
public class AdministradorService {

    @Autowired
    private AdministradorRepository administradorRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Administrador> obtenerTodos() {
        return administradorRepository.findAll();
    }

    // Este método ya lo tenías, asegúrate de usarlo para registrar admins
    public Administrador registrarAdministrador(Administrador admin) {
        if (administradorRepository.findByDniCuit(admin.getDniCuit()).isPresent()) {
            throw new IllegalArgumentException("El administrador ya existe.");
        }
        return administradorRepository.save(admin);
    }

    // Tu método actual de registrarCliente está bien si quieres mantener ambos
    public Cliente registrarCliente(Cliente nuevoCliente) {
        if (clienteRepository.findByDniCuit(nuevoCliente.getDniCuit()).isPresent()) {
            throw new IllegalArgumentException("El cliente ya existe.");
        }
        return clienteRepository.save(nuevoCliente);
    }

   public boolean verificarCredenciales(String dniCuit, String contraseña) {
    return administradorRepository.findByDniCuit(dniCuit)
            .map(admin -> {
                System.out.println("Login: Comparando DNI " + dniCuit);
                System.out.println("Base de datos tiene: " + admin.getContraseña());
                System.out.println("Usuario ingresó: " + contraseña);
                boolean coincide = admin.getContraseña().equals(contraseña);
                System.out.println("¿Coinciden? " + coincide);
                return coincide;
            })
            .orElseGet(() -> {
                System.out.println("Login: No se encontró administrador con DNI " + dniCuit);
                return false;
            });
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