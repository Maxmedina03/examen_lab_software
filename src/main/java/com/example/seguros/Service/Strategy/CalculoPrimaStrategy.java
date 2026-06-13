package com.example.seguros.Service.Strategy;

import java.math.BigDecimal;

import com.example.seguros.Model.Poliza;

public interface CalculoPrimaStrategy {

    boolean aplicaPara(Poliza poliza);

    BigDecimal calcular(Poliza poliza);
}
