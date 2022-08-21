package com.portfolio.caf.Service;

import com.portfolio.caf.Entity.Proyecto;
import com.portfolio.caf.Repository.RProyecto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
@Service
@Transactional
public class SProyecto {

    @Autowired
    RProyecto rProyecto;

    public List<Proyecto> list(){
        return rProyecto.findAll();
    }

    public Optional<Proyecto> getOne(Long id){
        return rProyecto.findById(id);
    }

    public Optional<Proyecto> getByNombreP(String nombreP){
        return rProyecto.findByNombreP(nombreP);
    }

    public void save(Proyecto Proyecto){
        rProyecto.save(Proyecto);
    }

    public void delete(Long id){
        rProyecto.deleteById(id);
    }

    public boolean existsById(Long id){
        return rProyecto.existsById(id);
    }

    public boolean existsByNombreP(String nombreP){
        return rProyecto.existsByNombreP(nombreP);
    }
}
