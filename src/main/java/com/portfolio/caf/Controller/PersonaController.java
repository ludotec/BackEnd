package com.portfolio.caf.Controller;

import com.portfolio.caf.Entity.Persona;
import com.portfolio.caf.Interface.IPersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin( origins = {"https://frontendcaf.web.app"}, allowCredentials = "true")
public class PersonaController {

    @Autowired
    private  IPersonaService ipersoService;

    @GetMapping("/personas/traer")
    public List<Persona> getPersonas(){
        return ipersoService.getPersonas();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/personas/crear")
    public String createStudent(@RequestBody Persona perso){
        ipersoService.savePersona(perso);
        //devuelve un string avisando si creó correctamente
        return "La Persona fué creada correctamente";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/personas/borrar/{id}")
    public String deletePersona(@PathVariable Long id){
        ipersoService.deletePersona(id);
        //devuelve un string avisando si eliminó correctamente
        return "La Persona fué eliminada correctamente";
    }

    @PutMapping("/personas/editar/{id}")
    public Persona editPersona(@PathVariable Long id,
                               @RequestParam("nombre") String nuevoNombre,
                               @RequestParam("apellido") String nuevoApellido,
                               @RequestParam("img") String nuevaImg){
        Persona perso =ipersoService.findPersona(id);

        //esto tambien puede ir en service
        //para desacoplar mejor aun el codigo en un nuevo metodo
        perso.setApellido(nuevoApellido);
        perso.setNombre(nuevoNombre);
        perso.setImg(nuevaImg);

        ipersoService.savePersona(perso);

        //retorna la nueva persona
        return perso;
    }

    @GetMapping("/personas/traer/perfil")
    public Persona finPersona(){
        return ipersoService.findPersona((long)2);
    }


}
