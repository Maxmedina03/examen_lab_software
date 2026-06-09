package com.example.seguros.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.seguros.Model.EstadoReclamo;
import com.example.seguros.Model.Reclamo;

@Repository
public interface ReclamoRepository extends JpaRepository<Reclamo, Long> {

    List<Reclamo> findByPolizaIdPoliza(Long polizaId);

    List<Reclamo> findByEstadoReclamo(EstadoReclamo estadoReclamo);

}
