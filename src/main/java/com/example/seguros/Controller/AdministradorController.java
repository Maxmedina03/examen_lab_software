package com.example.seguros.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.seguros.Model.Administrador;
import com.example.seguros.Model.Cliente;
import com.example.seguros.Service.AdministradorService;

@RestController
@RequestMapping("/api/administradores")
public class AdministradorController {

    @Autowired
    private AdministradorService administradorService;

    // Endpoint para registrar CLIENTES (el que ya tenías)
    @PostMapping("/registrar-cliente")
    public Cliente registrarCliente(@RequestBody Cliente nuevoCliente) {
        return administradorService.registrarCliente(nuevoCliente);
    }

    @PostMapping("/registrar-admin")
    public Administrador registrarAdmin(@RequestBody Administrador nuevoAdmin) {
        return administradorService.registrarAdministrador(nuevoAdmin);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Administrador credenciales) {
        boolean esValido = administradorService.verificarCredenciales(
                credenciales.getDniCuit(),
                credenciales.getContraseña());

        if (esValido) {
            return ResponseEntity.ok("Login exitoso");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas");
        }
    }
}