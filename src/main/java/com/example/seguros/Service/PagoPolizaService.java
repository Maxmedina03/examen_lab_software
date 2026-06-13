package com.example.seguros.Service;

import com.example.seguros.DTO.PagoPolizaRequest;
import com.example.seguros.Model.PagoPoliza;
import com.example.seguros.Model.Poliza;
import com.example.seguros.Repository.PagoPolizaRepository;
import com.example.seguros.Repository.PolizaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PagoPolizaService {

    private final PagoPolizaRepository pagoPolizaRepository;
    private final PolizaRepository polizaRepository;

    public PagoPolizaService(PagoPolizaRepository pagoPolizaRepository, PolizaRepository polizaRepository) {
        this.pagoPolizaRepository = pagoPolizaRepository;
        this.polizaRepository = polizaRepository;
    }

    @Transactional
    public List<PagoPoliza> guardarPagos(List<PagoPolizaRequest> pagosRequest) {
        List<PagoPoliza> pagosPoliza = new java.util.ArrayList<>();

        for (PagoPolizaRequest req : pagosRequest) {
            PagoPoliza pagoPoliza = new PagoPoliza();
            pagoPoliza.setFechaPago(req.getFechaPago());
            pagoPoliza.setMonto(req.getMonto());

            if (req.getMetodoPago() != null) {
                pagoPoliza.setMetodoPago(req.getMetodoPago());
            } else {
                throw new RuntimeException("El método de pago es obligatorio.");
            }
            if (req.getIdPoliza() != null) {
                Poliza polizaReal = polizaRepository.findById(req.getIdPoliza())
                        .orElseThrow(
                                () -> new RuntimeException("No se encontró la póliza con ID: " + req.getIdPoliza()));

                pagoPoliza.setPoliza(polizaReal);
            } else {
                throw new RuntimeException("Cada pago debe incluir un idPoliza válido.");
            }
            pagosPoliza.add(pagoPoliza);
        }
        return pagoPolizaRepository.saveAll(pagosPoliza);
    }

    public List<PagoPoliza> obtenerTodos() {
        return pagoPolizaRepository.findAll();
    }

    public List<PagoPoliza> obtenerPorPoliza(Long polizaId) {
        return pagoPolizaRepository.findByPolizaIdPoliza(polizaId);
    }

    public Optional<PagoPoliza> obtenerPorId(Long id) {
        return pagoPolizaRepository.findByIdPago(id);
    }


    public void eliminarPago(Long id) {
        if (!pagoPolizaRepository.existsById(id)) {
            throw new RuntimeException("No se puede eliminar: Pago no encontrado.");
        }
        pagoPolizaRepository.deleteById(id);
    }

    
}