package com.example.seguros.Controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.seguros.Model.*;
import com.example.seguros.Service.PolizaService;

@RestController
@RequestMapping("/api/polizas")
public class PolizaController {
    
    private final PolizaService polizaService;

    // Volvemos a necesitar solo tu PolizaService original
    public PolizaController(PolizaService polizaService){
        this.polizaService = polizaService;
    }

   
    @PostMapping("/automotor")
    public ResponseEntity<?> contratarAutomotores(@RequestBody List<PolizaAutomotor> polizasAuto){ // 👈 Cambiado a List<PolizaAutomotor>
        try {
            for (PolizaAutomotor auto : polizasAuto) {
                
                if (auto.getCliente() == null || auto.getCliente().getIdCliente() == null) {
                    return ResponseEntity.badRequest().body("Cada póliza debe incluir un objeto cliente con su idCliente.");
                }
                
                
                polizaService.emitirPoliza(auto); 
            }
            // 2. Guardamos todo el lote completo usando saveAll en el service
            // (Para esto tu guardarPolizas tiene que aceptar List<Poliza>)
            List<PolizaAutomotor> polizasGuardadas = polizaService.guardarPolizas(polizasAuto);
            
            return new ResponseEntity<>(polizasGuardadas, HttpStatus.CREATED);
        } catch(Exception e) { 
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

  
    @PostMapping("/hogar")
    public ResponseEntity<?> contratarHogar(@RequestBody List<PolizaHogar> polizasHogar){ // 👈 Cambiado a List<PolizaHogar>
        try {
           for (PolizaHogar hogar : polizasHogar) {
                // 1. Validamos que en el JSON venga el cliente con su ID colgado
                if (hogar.getCliente() == null || hogar.getCliente().getIdCliente() == null) {
                    return ResponseEntity.badRequest().body("Cada póliza debe incluir un objeto cliente con su idCliente.");
                }
                
              
                polizaService.emitirPoliza(hogar); 
            }
            
            // 2. Guardamos todo el lote completo usando saveAll en el service
            List<PolizaHogar> polizasGuardadas = polizaService.guardarPolizas(polizasHogar);
            
            return new ResponseEntity<>(polizasGuardadas, HttpStatus.CREATED);
        } catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    // --- Los métodos GET quedan exactamente igual que antes ---
    @GetMapping
    public ResponseEntity<List<Poliza>> obtenerTodas(){
        return ResponseEntity.ok(polizaService.obtenerTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Poliza> obtenerPorId(@PathVariable Long id){
        try{
            Poliza poliza = polizaService.obtenerPorId(id);
            return ResponseEntity.ok(poliza);
        } catch(RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}