package com.portfolio.caf.Controller;

import com.portfolio.caf.Dto.DtoProyecto;
import com.portfolio.caf.Entity.Proyecto;
import com.portfolio.caf.Security.Controller.Mensaje;
import com.portfolio.caf.Service.SProyecto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/proyecto")
@CrossOrigin( origins = {"https://frontendcaf.web.app"}, allowCredentials = "true")
public class CProyecto {

    @Autowired
    SProyecto sProyecto;

    @GetMapping("/lista")
    public ResponseEntity<List<Proyecto>> list(){
        List<Proyecto> list = sProyecto.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody DtoProyecto dtoProyecto) {
        if(StringUtils.isBlank(dtoProyecto.getNombreP()))
            return new ResponseEntity<>(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(sProyecto.existsByNombreP((dtoProyecto.getNombreP())))
            return new ResponseEntity<>(new Mensaje("Ese Proyecto existe"), HttpStatus.BAD_REQUEST);

        Proyecto proyecto = new Proyecto(dtoProyecto.getNombreP(), dtoProyecto.getDescripcionP(),dtoProyecto.getLenguage() ,dtoProyecto.getLinkP());
        sProyecto.save(proyecto);

        return new ResponseEntity<>(new Mensaje("Proyecto agregado"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody DtoProyecto dtoProyecto){
        if(!sProyecto.existsById(id))
            return new ResponseEntity<>(new Mensaje("El Id no existe"), HttpStatus.NOT_FOUND);

        if(sProyecto.existsByNombreP(dtoProyecto.getNombreP()) && sProyecto.getByNombreP(dtoProyecto.getNombreP()).get().getId() != id)
            return new ResponseEntity<>(new Mensaje("Ese Proyecto ya existe"), HttpStatus.BAD_REQUEST);

        if(StringUtils.isBlank(dtoProyecto.getNombreP()))
            return new ResponseEntity<>(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);

        Proyecto proyecto = sProyecto.getOne(id).get();
        proyecto.setNombreP(dtoProyecto.getNombreP());
        proyecto.setDescripcionP(dtoProyecto.getDescripcionP());

        sProyecto.save(proyecto);
        return new ResponseEntity<>(new Mensaje("Proyecto actualizado"), HttpStatus.OK);


    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Proyecto> getById(@PathVariable("id") Long id){
        if(!sProyecto.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Proyecto proyecto = sProyecto.getOne(id).get();
        return new ResponseEntity(proyecto, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        if(!sProyecto.existsById(id))
            return new ResponseEntity<>(new Mensaje("El Id no existe"), HttpStatus.NOT_FOUND);

        sProyecto.delete(id);

        return new ResponseEntity<>(new Mensaje("Proyecto eliminado"), HttpStatus.OK);
    }


}
