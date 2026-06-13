package com.example.seguros.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.seguros.Model.Administrador;
import java.util.Optional;

@Repository
public interface AdministradorRepository extends JpaRepository<Administrador, Long> {

    Optional<Administrador> findByIdAdmin(Long idAdmin);


    Optional<Administrador> findByEmail(String email);
    
    
    Optional<Administrador> findByDniCuit(String dniCuit);
}