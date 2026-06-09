package com.example.seguros.Service;

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

        // Base inicial según el tamaño de la propiedad (Metros cuadrados * $50)
        BigDecimal base = new BigDecimal(hogar.getMetrosCuadrados()).multiply(new BigDecimal("50.00"));
        
        // Lógica de Negocio: Si la casa posee alarma de seguridad activa, se bonifica (-15% de prima)
        if (hogar.isTieneAlarma()) {
            base = base.multiply(new BigDecimal("0.85"));
        }
        return base;
    }
}
