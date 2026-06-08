package com.example.seguros.Service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.seguros.Model.Cliente;
import com.example.seguros.Repository.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente crear(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public List<Cliente> obtenerTodos(){
        return clienteRepository.findAll();
    }

    public Cliente obtenerPorDni(String dni){
        return clienteRepository.findByDnicuit(dni)
            .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado con dni: " + dni));
    }
}
