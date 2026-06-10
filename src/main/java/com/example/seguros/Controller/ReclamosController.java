package com.example.seguros.Controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.seguros.DTO.ReclamoRequest;
import com.example.seguros.Model.EstadoReclamo;
import com.example.seguros.Model.Reclamo;
import com.example.seguros.Service.ReclamoService;

@RestController
@RequestMapping("/api/reclamos")
public class ReclamosController {

    // CORRECCIÓN: Usamos minúscula inicial (buena práctica) para la variable de instancia
    private final ReclamoService reclamoService;

    // Inyectamos el Service de forma limpia por constructor
    public ReclamosController(ReclamoService reclamoService){
        this.reclamoService = reclamoService;
    }

    // 1. Registrar lote de reclamos (POST http://localhost:8080/api/reclamos)
    @PostMapping
    public ResponseEntity<List<Reclamo>> registrarReclamos(@RequestBody List<ReclamoRequest> requests) {
    List<Reclamo> nuevosReclamos = reclamoService.guardarReclamos(requests);
    return new ResponseEntity<>(nuevosReclamos, HttpStatus.CREATED);
    }

    // 2. Obtener todos los reclamos (GET http://localhost:8080/api/reclamos)
    @GetMapping
    public ResponseEntity<List<Reclamo>> obtenerTodos() {
        // CORRECCIÓN: Reemplazamos javaReclamoService por reclamoService
        return ResponseEntity.ok(reclamoService.obtenerTodos());
    }

    // 3. Buscar reclamos por el ID de la póliza (GET http://localhost:8080/api/reclamos/poliza/{polizaId})
    @GetMapping("/poliza/{polizaId}")
    public ResponseEntity<List<Reclamo>> obtenerPorPoliza(@PathVariable Long polizaId) {
        return ResponseEntity.ok(reclamoService.obtenerPorPoliza(polizaId));
    }

    // 4. Buscar reclamos según su estado (GET http://localhost:8080/api/reclamos/estado/{estado})
    @GetMapping("/estado/{estado}")
    public ResponseEntity<?> obtenerPorEstado(@PathVariable String estado) {
        try {
            EstadoReclamo estadoEnum = EstadoReclamo.valueOf(estado.toUpperCase());
            // CORRECCIÓN: Reemplazamos javaReclamoService por reclamoService
            return ResponseEntity.ok(reclamoService.obtenerPorEstado(estadoEnum));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Estado no válido. Estados posibles: PENDIENTE, EN_PROCESO, APROBADO, RECHAZADO");
        }
    }
}