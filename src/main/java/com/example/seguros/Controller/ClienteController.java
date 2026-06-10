package com.example.seguros.Controller;

import java.util.List;

import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.seguros.Model.Cliente;
import com.example.seguros.Service.ClienteService;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    // Inyección por constructor limpia y profesional
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    // 1. Guardar un nuevo cliente (POST http://localhost:8080/api/clientes)
    @PostMapping
    public ResponseEntity<List<Cliente>> crearClientes(@RequestBody List<Cliente> clientes) { // 
        List<Cliente> nuevosClientes = clienteService.guardarCliente(clientes); // 
        return new ResponseEntity<>(nuevosClientes, HttpStatus.CREATED); // Devuelve el estado 201 con la lista completa
    }

    // 2. Obtener la lista de todos los clientes (GET http://localhost:8080/api/clientes)
    @GetMapping
    public ResponseEntity<List<Cliente>> obtenerTodos() {
        List<Cliente> clientes = clienteService.obtenerTodos();
        return ResponseEntity.ok(clientes);
    }

    // 3. Buscar un cliente específico por su DNI (GET http://localhost:8080/api/clientes/buscar/{dni})
    @GetMapping("/buscar/{dni}")
    public ResponseEntity<Cliente> obtenerPorDni(@PathVariable String dni) {
        try {
            Cliente cliente = clienteService.obtenerPorDni(dni);
            return ResponseEntity.ok(cliente);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Devuelve 404 si no existe
        }
    }

    
}
