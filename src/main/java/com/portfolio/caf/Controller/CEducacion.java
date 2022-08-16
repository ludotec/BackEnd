package com.portfolio.caf.Controller;

import com.portfolio.caf.Dto.DtoEducacion;
import com.portfolio.caf.Entity.Educacion;
import com.portfolio.caf.Security.Controller.Mensaje;
import com.portfolio.caf.Service.SEducacion;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/educacion")
@CrossOrigin( origins = {"http://localhost:4200"}, allowCredentials = "true")
public class CEducacion {
    @Autowired
    SEducacion sEducacion;

    @GetMapping("/lista")
    public ResponseEntity<List<Educacion>> list(){
        List<Educacion> list = sEducacion.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody DtoEducacion dtoEducacion) {
        if(StringUtils.isBlank(dtoEducacion.getNombreE()))
            return new ResponseEntity<>(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(sEducacion.existsByNombreE((dtoEducacion.getNombreE())))
            return new ResponseEntity<>(new Mensaje("Esa educación existe"), HttpStatus.BAD_REQUEST);

        Educacion educacion = new Educacion(dtoEducacion.getNombreE(), dtoEducacion.getDescripcionE());
        sEducacion.save(educacion);

        return new ResponseEntity<>(new Mensaje("Educación agregada"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody DtoEducacion dtoEducacion){
        if(!sEducacion.existsById(id))
            return new ResponseEntity<>(new Mensaje("El Id no existe"), HttpStatus.NOT_FOUND);

        if(sEducacion.existsByNombreE(dtoEducacion.getNombreE()) && sEducacion.getByNombreE(dtoEducacion.getNombreE()).get().getId() != id)
            return new ResponseEntity<>(new Mensaje("Esa educación ya existe"), HttpStatus.BAD_REQUEST);

        if(StringUtils.isBlank(dtoEducacion.getNombreE()))
            return new ResponseEntity<>(new Mensaje("El campo no puede estar vacío"), HttpStatus.BAD_REQUEST);

        Educacion educacion = sEducacion.getOne(id).get();
        educacion.setNombreE(dtoEducacion.getNombreE());
        educacion.setDescripcionE(dtoEducacion.getDescripcionE());

        sEducacion.save(educacion);
        return new ResponseEntity<>(new Mensaje("Educación actualizada"), HttpStatus.OK);


    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Educacion> getById(@PathVariable("id") Long id){
        if(!sEducacion.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Educacion educacion = sEducacion.getOne(id).get();
        return new ResponseEntity(educacion, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        if(!sEducacion.existsById(id))
            return new ResponseEntity<>(new Mensaje("El Id no existe"), HttpStatus.NOT_FOUND);

        sEducacion.delete(id);

        return new ResponseEntity<>(new Mensaje("Educacion eliminada"), HttpStatus.OK);
    }

}
