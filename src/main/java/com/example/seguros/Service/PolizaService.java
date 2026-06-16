package com.example.seguros.Service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.seguros.Model.Poliza;
import com.example.seguros.Repository.PolizaRepository;
import com.example.seguros.Service.Strategy.CalculoPrimaStrategy;

@Service
public class PolizaService {

    private final PolizaRepository polizaRepository;
    private final List<CalculoPrimaStrategy> estrategiasCalculo;

    public PolizaService(PolizaRepository polizaRepository, List<CalculoPrimaStrategy> estrategiasCalculo) {
        this.polizaRepository = polizaRepository;
        this.estrategiasCalculo = estrategiasCalculo;
    }

    public Poliza emitirPoliza(Poliza poliza) {
        CalculoPrimaStrategy estrategiaAdecuada = estrategiasCalculo.stream()
                .filter(est -> est.aplicaPara(poliza))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "No existe una estrategia de cálculo para este tipo de póliza"));

        BigDecimal primaCalculada = estrategiaAdecuada.calcular(poliza);
        poliza.setPrima(primaCalculada);

        poliza.calcularPrima();

        return poliza;
    }

    public <T extends Poliza> List<T> guardarPolizas(List<T> polizas) {
        return polizaRepository.saveAll(polizas);
    }

    public List<Poliza> obtenerTodas() {
        return polizaRepository.findAll();
    }

    public Poliza obtenerPorId(Long id) {
        return polizaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Poliza no encontrada con id: " + id));
    }

    public Poliza actualizarPoliza(Long id, Poliza polizaActualizada) {
        // 1. Buscamos la póliza existente
        Poliza polizaExistente = obtenerPorId(id);

        // 2. Actualizamos todos los campos directamente
        polizaExistente.setFechaInicio(polizaActualizada.getFechaInicio());
        polizaExistente.setFechaFin(polizaActualizada.getFechaFin());
        polizaExistente.setEstadoPoliza(polizaActualizada.getEstadoPoliza());
        polizaExistente.setCoberturaMaxima(polizaActualizada.getCoberturaMaxima());

        // 3. Guardamos los cambios
        return polizaRepository.save(polizaExistente);
    }

    public void eliminarPoliza(Long id) {
        if (!polizaRepository.existsById(id)) {
            throw new IllegalArgumentException("No se puede eliminar: Póliza no encontrada.");
        }
        polizaRepository.deleteById(id);
    }

}
