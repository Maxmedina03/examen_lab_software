package com.example.seguros.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.seguros.Model.PolizaHogar;
@Repository
public interface PolizaHogarRepository extends JpaRepository<PolizaHogar, Long> {

}
