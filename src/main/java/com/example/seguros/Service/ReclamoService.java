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
            Reclamo reclamo = new Reclamo();
            reclamo.setFechaSiniestro(req.getFechaSiniestro());
            reclamo.setFechaReclamo(req.getFechaReclamo());
            reclamo.setDescripcionHechos(req.getDescripcionHechos());
            reclamo.setMontoReclamado(req.getMontoReclamado());
            reclamo.setEstadoReclamo(EstadoReclamo.PENDIENTE); // Se asigna el estado inicial por defecto

            if (req.getIdPoliza() != null) {
                // JPA busca en la base de datos y recupera la instancia hija concreta real
                Poliza polizaReal = polizaRepository.findById(req.getIdPoliza())
                        .orElseThrow(() -> new RuntimeException("No se encontró la póliza con ID: " + req.getIdPoliza()));
                
                reclamo.setPoliza(polizaReal); // Se vincula el objeto polimórfico real
            } else {
                throw new RuntimeException("Cada reclamo debe incluir un idPoliza válido.");
            }

            reclamosAGuardar.add(reclamo);
        }

        return reclamoRepository.saveAll(reclamosAGuardar);
    }

    /**
     * Obtiene la lista completa de reclamos registrados.
     */
    public List<Reclamo> obtenerTodos() {
        return reclamoRepository.findAll();
    }

    /**
     * Busca reclamos asociados a una póliza específica.
     */
    public List<Reclamo> obtenerPorPoliza(Long idPoliza) {
        return reclamoRepository.findByPolizaIdPoliza(idPoliza);
    }

    /**
     * Filtra los reclamos por su estado actual (PENDIENTE, EN_PROCESO, APROBADO, RECHAZADO).
     */
    public List<Reclamo> obtenerPorEstado(EstadoReclamo estadoReclamo) {
        return reclamoRepository.findByEstadoReclamo(estadoReclamo);
    }
}