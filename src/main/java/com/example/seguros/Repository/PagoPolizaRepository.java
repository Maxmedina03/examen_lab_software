package com.example.seguros.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.seguros.Model.PagoPoliza;

@Repository
public interface PagoPolizaRepository extends JpaRepository<PagoPoliza, Long> {
    
    List<PagoPoliza> findByPolizaIdPoliza(Long polizaId); 

    Optional<PagoPoliza> findByIdPago(Long idPago);

    
}