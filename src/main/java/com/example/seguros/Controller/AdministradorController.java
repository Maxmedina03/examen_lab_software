package com.example.seguros.Controller;

import com.example.seguros.Model.Cliente;
import com.example.seguros.Service.AdministradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/administradores")
public class AdministradorController {

    @Autowired
    private AdministradorService administradorService;

    
    @PostMapping("/registrar-cliente")
    public Cliente registrarCliente(@RequestBody Cliente nuevoCliente) {
        return administradorService.registrarCliente(nuevoCliente);
    }
}