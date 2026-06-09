package com.example.seguros.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.seguros.Model.Poliza;

@Repository
public interface PolizaRepository extends JpaRepository<Poliza, Long> {

    Optional<Poliza> findByNumeroPoliza(String numeroPoliza);

    List<Poliza> findByClienteIdCliente(Long clienteId);

    List<Poliza> findByTipoPoliza(String tipoPoliza);
}
