package com.example.seguros.Controller;

import java.util.List;
import java.util.Map;

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

import com.example.seguros.DTO.ReclamoRequest;
import com.example.seguros.Model.EstadoReclamo;
import com.example.seguros.Model.Reclamo;
import com.example.seguros.Service.ReclamoService;

@RestController
@RequestMapping("/api/reclamos")
public class ReclamosController {

    private final ReclamoService reclamoService;

    public ReclamosController(ReclamoService reclamoService) {
        this.reclamoService = reclamoService;
    }

    @PostMapping
    public ResponseEntity<List<Reclamo>> registrarReclamos(@RequestBody List<ReclamoRequest> requests) {
        List<Reclamo> nuevosReclamos = reclamoService.guardarReclamos(requests);
        return new ResponseEntity<>(nuevosReclamos, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Reclamo>> obtenerTodos() {
        return ResponseEntity.ok(reclamoService.obtenerTodos());
    }

    @GetMapping("/poliza/{polizaId}")
    public ResponseEntity<List<Reclamo>> obtenerPorPoliza(@PathVariable Long polizaId) {
        return ResponseEntity.ok(reclamoService.obtenerPorPoliza(polizaId));
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<?> obtenerPorEstado(@PathVariable String estado) {
        try {
            EstadoReclamo estadoEnum = EstadoReclamo.valueOf(estado.toUpperCase());
            return ResponseEntity.ok(reclamoService.obtenerPorEstado(estadoEnum));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body("Estado no válido. Estados posibles: PENDIENTE, EN_PROCESO, APROBADO, RECHAZADO");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarReclamo(@PathVariable Long id, @RequestBody Map<String, String> body) {
        try {
            // Obtenemos el nuevo estado desde el cuerpo del JSON
            String nuevoEstadoStr = body.get("estadoReclamo");
            EstadoReclamo estado = EstadoReclamo.valueOf(nuevoEstadoStr.toUpperCase());

            // Llamamos al método correcto del servicio
            Reclamo actualizado = reclamoService.actualizarEstadoReclamo(id, estado);
            return ResponseEntity.ok(actualizado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al actualizar: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarReclamo(@PathVariable Long id) {
        try {
            reclamoService.eliminarReclamo(id);
            return ResponseEntity.ok("Reclamo eliminado correctamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}