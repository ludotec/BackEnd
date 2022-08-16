package com.portfolio.caf.Service;

import com.portfolio.caf.Entity.Persona;
import com.portfolio.caf.Interface.IPersonaService;
import com.portfolio.caf.Repository.IPersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImpPersonaService implements IPersonaService {
    @Autowired
    private IPersonaRepository persoRepository;

    @Override
    public List<Persona> getPersonas() {
        List<Persona> listaPersonas = persoRepository.findAll();
        return listaPersonas;
    }

    //método para dar de alta una persona
    @Override
    public void savePersona(Persona perso) {
        persoRepository.save(perso);
    }

    //método para borrar una persona
    @Override
    public void deletePersona(Long id) {
        persoRepository.deleteById(id);
    }

    //método para encontrar una persona
    @Override
    public Persona findPersona(Long id) {
        //acá si no encuentro la persona devuelvo null por eso va el orElse
        Persona perso = persoRepository.findById(id).orElse(null);
        return perso;

    }
}