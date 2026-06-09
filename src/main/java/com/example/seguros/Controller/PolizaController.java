package com.example.seguros.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.seguros.Model.Poliza;
import com.example.seguros.Service.PolizaService;

@RestController
@RequestMapping("/api/polizas")
public class PolizaController {
    
    private final PolizaService polizaService;

    public PolizaController(PolizaService polizaService){
        this.polizaService = polizaService;
    }

    @PostMapping
    public ResponseEntity<?> contratarPoliza(@RequestBody Poliza poliza){
        try{
            Poliza polizaEmitida = polizaService.emitirPoliza(poliza);

            Poliza polizaGuardada = polizaService.guardarPoliza(polizaEmitida);

            return new ResponseEntity<>(polizaGuardada, HttpStatus.CREATED);
        } catch(IllegalArgumentException e) {
            // Si mandan un tipo de póliza inválido, capturamos el error de la estrategia
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Poliza>> obtenerTodas(){
        return ResponseEntity.ok(polizaService.obtenerTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Poliza> obtenerPorId(@PathVariable Long id){
        try{
            Poliza poliza = polizaService.obtenerPorId(id);
            return ResponseEntity.ok(poliza);
        } catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
