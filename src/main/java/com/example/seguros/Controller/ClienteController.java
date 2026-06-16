package com.example.seguros.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.seguros.Model.Cliente;
import com.example.seguros.Service.ClienteService;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity<List<Cliente>> crearClientes(@RequestBody List<Cliente> clientes) { //
        List<Cliente> nuevosClientes = clienteService.guardarCliente(clientes); //
        return new ResponseEntity<>(nuevosClientes, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> obtenerTodos() {
        List<Cliente> clientes = clienteService.obtenerTodos();
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/buscar/{dni}")
    public ResponseEntity<Cliente> obtenerPorDniCuit(@PathVariable String dniCuit) {
        try {
            Cliente cliente = clienteService.obtenerPorDniCuit(dniCuit);
            return ResponseEntity.ok(cliente);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{dniCuit}")
    public ResponseEntity<Cliente> actualizarCliente(@PathVariable String dniCuit, @RequestBody Cliente cliente) {
        Cliente actualizado = clienteService.actualizarCliente(dniCuit, cliente);
        return ResponseEntity.ok(actualizado);
    }

   @DeleteMapping("/{dniCuit}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable String dniCuit) {
        clienteService.eliminarClientePorDni(dniCuit);
        return ResponseEntity.noContent().build();
    }

}
