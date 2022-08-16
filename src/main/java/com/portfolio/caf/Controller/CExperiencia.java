package com.portfolio.caf.Controller;

import com.portfolio.caf.Dto.DtoExperiencia;
import com.portfolio.caf.Entity.Experiencia;
import com.portfolio.caf.Security.Controller.Mensaje;
import com.portfolio.caf.Service.SExperiencia;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("explab")
@CrossOrigin( origins = {"http://localhost:4200"}, allowCredentials = "true")
public class CExperiencia {

    @Autowired
    SExperiencia sExperiencia;

    @GetMapping("/lista")
    public ResponseEntity<List<Experiencia>> list(){
        List<Experiencia> list = sExperiencia.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody DtoExperiencia dtoExperiencia) {
        if(StringUtils.isBlank(dtoExperiencia.getNombreE()))
            return new ResponseEntity<>(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(sExperiencia.existsByNombreE((dtoExperiencia.getNombreE())))
            return new ResponseEntity<>(new Mensaje("Esa experiencia existe"), HttpStatus.BAD_REQUEST);

        Experiencia experiencia = new Experiencia(dtoExperiencia.getNombreE(), dtoExperiencia.getDescripcionE());
        sExperiencia.save(experiencia);

        return new ResponseEntity<>(new Mensaje("Experiencia agregada"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
     public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody DtoExperiencia dtoExperiencia){
        if(!sExperiencia.existsById(id))
            return new ResponseEntity<>(new Mensaje("El Id no existe"), HttpStatus.NOT_FOUND);

        if(sExperiencia.existsByNombreE(dtoExperiencia.getNombreE()) && sExperiencia.getByNombreE(dtoExperiencia.getNombreE()).get().getId() != id)
            return new ResponseEntity<>(new Mensaje("Esa experiencia ya existe"), HttpStatus.BAD_REQUEST);

        if(StringUtils.isBlank(dtoExperiencia.getNombreE()))
            return new ResponseEntity<>(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);

        Experiencia experiencia = sExperiencia.getOne(id).get();
        experiencia.setNombreE(dtoExperiencia.getNombreE());
        experiencia.setDescripcionE(dtoExperiencia.getDescripcionE());

        sExperiencia.save(experiencia);
        return new ResponseEntity<>(new Mensaje("Experiencia actualizada"), HttpStatus.OK);


    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Experiencia> getById(@PathVariable("id") Long id){
        if(!sExperiencia.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Experiencia experiencia = sExperiencia.getOne(id).get();
        return new ResponseEntity(experiencia, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        if(!sExperiencia.existsById(id))
            return new ResponseEntity<>(new Mensaje("El Id no existe"), HttpStatus.NOT_FOUND);

        sExperiencia.delete(id);

        return new ResponseEntity<>(new Mensaje("Experiencia eliminada"), HttpStatus.OK);
    }

}
