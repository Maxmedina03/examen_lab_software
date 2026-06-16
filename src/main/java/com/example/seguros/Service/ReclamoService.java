package com.example.seguros.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.seguros.DTO.ReclamoRequest;
import com.example.seguros.Model.EstadoReclamo;
import com.example.seguros.Model.Poliza;
import com.example.seguros.Model.Reclamo;
import com.example.seguros.Repository.PolizaRepository;
import com.example.seguros.Repository.ReclamoRepository;

@Service
public class ReclamoService {

    private final ReclamoRepository reclamoRepository;
    private final PolizaRepository polizaRepository;

   
    public ReclamoService(ReclamoRepository reclamoRepository, PolizaRepository polizaRepository) {
        this.reclamoRepository = reclamoRepository;
        this.polizaRepository = polizaRepository;
    }

   
    @Transactional
    public List<Reclamo> guardarReclamos(List<ReclamoRequest> requests) {
        List<Reclamo> reclamosAGuardar = new java.util.ArrayList<>();
        for (ReclamoRequest req : requests) {
            reclamosAGuardar.add(mapToEntity(req));
        }
        return reclamoRepository.saveAll(reclamosAGuardar);
    }

    
    public List<Reclamo> obtenerTodos() {
        return reclamoRepository.findAll();
    }

    public Optional<Reclamo> obtenerPorIdReclamo(Long idReclamo) {
        return reclamoRepository.findByidReclamo(idReclamo);
    }

  
    public List<Reclamo> obtenerPorPoliza(Long idPoliza) {
        return reclamoRepository.findByPolizaIdPoliza(idPoliza);
    }

    
    public List<Reclamo> obtenerPorEstado(EstadoReclamo estadoReclamo) {
        return reclamoRepository.findByEstadoReclamo(estadoReclamo);
    }

   
    @Transactional
    public Reclamo actualizarEstadoReclamo(Long idReclamo, EstadoReclamo nuevoEstado) {
       
        Reclamo reclamoExistente = obtenerPorIdReclamo(idReclamo)
                .orElseThrow(() -> new RuntimeException("Reclamo no encontrado con ID: " + idReclamo));

        reclamoExistente.setEstadoReclamo(nuevoEstado);

        return reclamoRepository.save(reclamoExistente);
    }

   
    public void eliminarReclamo(Long id) {
        if (!reclamoRepository.existsById(id)) {
            throw new RuntimeException("No se puede eliminar: Reclamo no encontrado con ID: " + id);
        }
        reclamoRepository.deleteById(id);
    }

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