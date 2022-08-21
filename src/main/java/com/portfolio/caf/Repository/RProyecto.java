package com.portfolio.caf.Repository;


import com.portfolio.caf.Entity.Proyecto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RProyecto extends JpaRepository<Proyecto, Long> {

    Optional<Proyecto> findByNombreP(String nombreP);
    boolean existsByNombreP(String nombreP);
}
