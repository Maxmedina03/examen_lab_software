package com.example.seguros.Service.Strategy;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.example.seguros.Model.Poliza;
import com.example.seguros.Model.PolizaHogar;
import com.example.seguros.Model.TipoPoliza;

@Component
public class CalculoPrimaHogarStrategy implements CalculoPrimaStrategy{
    @Override
    public boolean aplicaPara(Poliza poliza){
        return TipoPoliza.HOGAR.equals(poliza.getTipoPoliza());
    }

    @Override
    public BigDecimal calcular(Poliza poliza){
        PolizaHogar hogar = (PolizaHogar) poliza;

        
        BigDecimal base = new BigDecimal(hogar.getMetrosCuadrados()).multiply(new BigDecimal("50.00"));
        
        
        if (hogar.isTieneAlarma()) {
            base = base.multiply(new BigDecimal("0.85"));
        }
        return base;
    }
}
