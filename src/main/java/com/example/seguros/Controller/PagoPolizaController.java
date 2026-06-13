package com.example.seguros.Controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.seguros.DTO.PagoPolizaRequest;
import com.example.seguros.Model.PagoPoliza;
import com.example.seguros.Service.PagoPolizaService;     

@RestController
@RequestMapping("/api/pagos")
public class PagoPolizaController {

    private final PagoPolizaService pagoPolizaService;
    

   
    public PagoPolizaController(PagoPolizaService pagoPolizaService) {
        this.pagoPolizaService = pagoPolizaService;
        
    }

    
    @PostMapping
    public ResponseEntity<?> registrarPago(@RequestBody PagoPolizaRequest pagoPolizaRequest) {
        try {
            List<PagoPoliza> nuevoPago = pagoPolizaService.guardarPagos(List.of(pagoPolizaRequest)); // 
            return new ResponseEntity<>(nuevoPago, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

   
    @GetMapping
    public ResponseEntity<List<PagoPoliza>> obtenerTodos() {
        List<PagoPoliza> pagos = pagoPolizaService.obtenerTodos();
        return ResponseEntity.ok(pagos);
    }

  
    @GetMapping("/poliza/{polizaId}")
    public ResponseEntity<List<PagoPoliza>> obtenerPorPoliza(@PathVariable Long polizaId) {
        List<PagoPoliza> pagos = pagoPolizaService.obtenerPorPoliza(polizaId);
        return ResponseEntity.ok(pagos);
    }

   
    @GetMapping("/{id}")
    public ResponseEntity<PagoPoliza> obtenerPorId(@PathVariable Long id) {
        return pagoPolizaService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}