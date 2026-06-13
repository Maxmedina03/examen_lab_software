package com.example.seguros.Service;

import com.example.seguros.DTO.ReclamoRequest;
import com.example.seguros.Model.EstadoReclamo;
import com.example.seguros.Model.Poliza;
import com.example.seguros.Model.Reclamo;
import com.example.seguros.Repository.PolizaRepository;
import com.example.seguros.Repository.ReclamoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ReclamoService {

    private final ReclamoRepository reclamoRepository;
    private final PolizaRepository polizaRepository;

    // Inyección limpia por constructor
    public ReclamoService(ReclamoRepository reclamoRepository, PolizaRepository polizaRepository) {
        this.reclamoRepository = reclamoRepository;
        this.polizaRepository = polizaRepository;
    }

    /**
     * Guarda una lista de reclamos en lote de manera transaccional.
     * Si uno falla, se revierte toda la operación (rollback).
     */
    @Transactional
    public List<Reclamo> guardarReclamos(List<ReclamoRequest> requests) {
        List<Reclamo> reclamosAGuardar = new java.util.ArrayList<>();
        for (ReclamoRequest req : requests) {
            reclamosAGuardar.add(mapToEntity(req));
        }
        return reclamoRepository.saveAll(reclamosAGuardar);
    }

    /**
     * Obtiene la lista completa de reclamos registrados.
     */
    public List<Reclamo> obtenerTodos() {
        return reclamoRepository.findAll();
    }

    public Optional<Reclamo> obtenerPorIdReclamo(Long idReclamo) {
        return reclamoRepository.findByidReclamo(idReclamo);
    }

    /**
     * Busca reclamos asociados a una póliza específica.
     */
    public List<Reclamo> obtenerPorPoliza(Long idPoliza) {
        return reclamoRepository.findByPolizaIdPoliza(idPoliza);
    }

    /**
     * Filtra los reclamos por su estado actual (PENDIENTE, EN_PROCESO, APROBADO,
     * RECHAZADO).
     */
    public List<Reclamo> obtenerPorEstado(EstadoReclamo estadoReclamo) {
        return reclamoRepository.findByEstadoReclamo(estadoReclamo);
    }

    // --- UPDATE ---
    @Transactional
    public Reclamo actualizarEstadoReclamo(Long idReclamo, EstadoReclamo nuevoEstado) {
        // 1. Buscamos y extraemos el objeto real
        Reclamo reclamoExistente = obtenerPorIdReclamo(idReclamo)
                .orElseThrow(() -> new RuntimeException("Reclamo no encontrado con ID: " + idReclamo));

        reclamoExistente.setEstadoReclamo(nuevoEstado);

        return reclamoRepository.save(reclamoExistente);
    }

    // --- DELETE ---
    public void eliminarReclamo(Long id) {
        if (!reclamoRepository.existsById(id)) {
            throw new RuntimeException("No se puede eliminar: Reclamo no encontrado con ID: " + id);
        }
        reclamoRepository.deleteById(id);
    }

    // --- MÉTODOS AUXILIARES (Refactorización para limpieza) ---
    private Reclamo mapToEntity(ReclamoRequest req) {
        Reclamo reclamo = new Reclamo();
        reclamo.setFechaSiniestro(req.getFechaSiniestro());
        reclamo.setFechaReclamo(req.getFechaReclamo());
        reclamo.setDescripcionHechos(req.getDescripcionHechos());
        reclamo.setMontoReclamado(req.getMontoReclamado());
        reclamo.setEstadoReclamo(EstadoReclamo.PENDIENTE);

        if (req.getIdPoliza() != null) {
            Poliza poliza = polizaRepository.findById(req.getIdPoliza())
                    .orElseThrow(() -> new RuntimeException("No se encontró la póliza con ID: " + req.getIdPoliza()));
            reclamo.setPoliza(poliza);
        } else {
            throw new RuntimeException("Cada reclamo debe incluir un idPoliza válido.");
        }
        return reclamo;
    }
}