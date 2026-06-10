package com.example.seguros.Service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.seguros.Model.Poliza;
import com.example.seguros.Repository.PolizaRepository;

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
                .orElseThrow(() -> new IllegalArgumentException("No existe una estrategia de cálculo para este tipo de póliza"));

        // Aplica el Patrón Strategy
        BigDecimal primaCalculada = estrategiaAdecuada.calcular(poliza);
        poliza.setPrima(primaCalculada);
        
        // Ejecuta validaciones lógicas internas de la entidad
        poliza.calcularPrima();

        return poliza; // Retorna el objeto procesado
    }
    
    public <T extends Poliza> List<T> guardarPolizas(List<T> polizas) {
    return polizaRepository.saveAll(polizas);
    }

    public List<Poliza> obtenerTodas(){
        return polizaRepository.findAll();
    }

    public Poliza obtenerPorId(Long id){
        return polizaRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Poliza no encontrada con id: " + id));
    }

}
