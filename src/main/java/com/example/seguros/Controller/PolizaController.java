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

import com.example.seguros.Model.Poliza;
import com.example.seguros.Model.PolizaAutomotor;
import com.example.seguros.Model.PolizaHogar;
import com.example.seguros.Service.PolizaService;

@RestController
@RequestMapping("/api/polizas")
public class PolizaController {

    private final PolizaService polizaService;

    public PolizaController(PolizaService polizaService) {
        this.polizaService = polizaService;
    }

    @PostMapping("/automotor")
    public ResponseEntity<?> contratarAutomotores(@RequestBody List<PolizaAutomotor> polizasAuto) { // 👈 Cambiado a
                                                                                                    // List<PolizaAutomotor>
        try {
            for (PolizaAutomotor auto : polizasAuto) {

                if (auto.getCliente() == null || auto.getCliente().getIdCliente() == null) {
                    return ResponseEntity.badRequest()
                            .body("Cada póliza debe incluir un objeto cliente con su idCliente.");
                }

                polizaService.emitirPoliza(auto);
            }

            List<PolizaAutomotor> polizasGuardadas = polizaService.guardarPolizas(polizasAuto);

            return new ResponseEntity<>(polizasGuardadas, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/hogar")
    public ResponseEntity<?> contratarHogar(@RequestBody List<PolizaHogar> polizasHogar) {
        try {
            for (PolizaHogar hogar : polizasHogar) {

                if (hogar.getCliente() == null || hogar.getCliente().getIdCliente() == null) {
                    return ResponseEntity.badRequest()
                            .body("Cada póliza debe incluir un objeto cliente con su idCliente.");
                }

                polizaService.emitirPoliza(hogar);
            }

            List<PolizaHogar> polizasGuardadas = polizaService.guardarPolizas(polizasHogar);

            return new ResponseEntity<>(polizasGuardadas, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Poliza>> obtenerTodas() {
        return ResponseEntity.ok(polizaService.obtenerTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Poliza> obtenerPorId(@PathVariable Long id) {
        try {
            Poliza poliza = polizaService.obtenerPorId(id);
            return ResponseEntity.ok(poliza);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Poliza> actualizarPoliza(@PathVariable Long id, @RequestBody Poliza poliza) {
        System.out.println("Recibiendo actualización para ID: " + id);
        

        Poliza actualizado = polizaService.actualizarPoliza(id, poliza);
        return ResponseEntity.ok(actualizado);
    }

     @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarPoliza(@PathVariable Long id) {
        try {
            polizaService.eliminarPoliza(id);
            return ResponseEntity.ok("Póliza eliminada correctamente");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar la póliza: " + e.getMessage());
        }
    }   
}