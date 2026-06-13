package com.example.seguros.Service.Strategy;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.example.seguros.Model.Poliza;
import com.example.seguros.Model.PolizaAutomotor;
import com.example.seguros.Model.TipoPoliza;

@Component
public class CalcularPrimaAutomotorStrategy implements CalculoPrimaStrategy {

    @Override
    public boolean aplicaPara(Poliza poliza) {
        return TipoPoliza.AUTOMOTRIZ.equals(poliza.getTipoPoliza());
    }

    @Override
    public BigDecimal calcular(Poliza poliza) {
        PolizaAutomotor auto = (PolizaAutomotor) poliza;
        
        
        BigDecimal base = auto.getCoberturaMaxima().multiply(new BigDecimal("0.01"));
        
        
        int antiguedad = LocalDate.now().getYear() - auto.getAnio();
        if (antiguedad > 10) {
            base = base.multiply(new BigDecimal("1.20"));
    }

    return base;
    }

}
