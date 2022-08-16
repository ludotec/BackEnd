package com.portfolio.caf.Repository;

import com.portfolio.caf.Entity.Educacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface REducacion extends JpaRepository<Educacion, Long> {
    Optional<Educacion> findByNombreE(String nombreE);
    boolean existsByNombreE(String nombreE);
}
