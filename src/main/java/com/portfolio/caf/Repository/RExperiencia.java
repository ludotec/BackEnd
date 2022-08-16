package com.portfolio.caf.Repository;

import com.portfolio.caf.Entity.Experiencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RExperiencia extends JpaRepository<Experiencia, Long>  {
    Optional<Experiencia> findByNombreE(String nombreE);
    boolean existsByNombreE(String nombreE);
}
