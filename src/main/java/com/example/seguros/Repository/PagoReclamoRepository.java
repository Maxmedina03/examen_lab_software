package com.example.seguros.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.seguros.Model.PagoReclamo;

@Repository
public interface PagoReclamoRepository extends JpaRepository<PagoReclamo, Long> {

    List<PagoReclamo> findByReclamoId(Long reclamoId);
}
