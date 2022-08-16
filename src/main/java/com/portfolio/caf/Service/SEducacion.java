package com.portfolio.caf.Service;


import com.portfolio.caf.Entity.Educacion;
import com.portfolio.caf.Repository.REducacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SEducacion {
    @Autowired
    REducacion rEducacion;

    public List<Educacion> list(){
        return rEducacion.findAll();
    }

    public Optional<Educacion> getOne(Long id){
        return rEducacion.findById(id);
    }

    public Optional<Educacion> getByNombreE(String nombreE){
        return rEducacion.findByNombreE(nombreE);
    }

    public void save(Educacion Educacion){
        rEducacion.save(Educacion);
    }

    public void delete(Long id){
        rEducacion.deleteById(id);
    }

    public boolean existsById(Long id){
        return rEducacion.existsById(id);
    }

    public boolean existsByNombreE(String nombreE){
        return rEducacion.existsByNombreE(nombreE);
    }
}
