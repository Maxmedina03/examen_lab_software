package com.example.seguros.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.seguros.Model.PolizaAutomotor;

@Repository
public interface PolizaAutomotorRepository  extends JpaRepository<PolizaAutomotor, Long>{

    Optional<PolizaAutomotor> findByPatente(String patente);


}
