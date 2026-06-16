package com.example.seguros.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.seguros.Model.Cliente;
import com.example.seguros.Repository.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> guardarCliente(List<Cliente> cliente) {
        return clienteRepository.saveAll(cliente);
    }

    public List<Cliente> obtenerTodos() {
        return clienteRepository.findAll();
    }

    public Cliente obtenerPorDniCuit(String dniCuit) {
        return clienteRepository.findByDniCuit(dniCuit)
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado con dni: " + dniCuit));
    }

    public Cliente actualizarCliente(String dniCuit, Cliente clienteActualizado) {
        Cliente clienteExistente = obtenerPorDniCuit(dniCuit);

        clienteExistente.setNombre(clienteActualizado.getNombre());
        clienteExistente.setApellido(clienteActualizado.getApellido());
        clienteExistente.setEmail(clienteActualizado.getEmail());
        clienteExistente.setDireccion(clienteActualizado.getDireccion());
        clienteExistente.setTelefono(clienteActualizado.getTelefono());

        return clienteRepository.save(clienteExistente);
    }

    public void eliminarClientePorDni(String dniCuit) {
        Cliente cliente = clienteRepository.findByDniCuit(dniCuit)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        // Validación: si el cliente tiene pólizas, no se puede borrar.
        if (!cliente.getPolizas().isEmpty()) {
            throw new RuntimeException("No se puede eliminar: el cliente tiene pólizas asociadas.");
        }

        clienteRepository.delete(cliente);
    }
}
