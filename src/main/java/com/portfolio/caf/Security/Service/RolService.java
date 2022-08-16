package com.portfolio.caf.Security.Service;

import com.portfolio.caf.Security.Entity.Rol;
import com.portfolio.caf.Security.Enums.RolNombre;
import com.portfolio.caf.Security.Repository.IRolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class RolService {

    @Autowired
    IRolRepository iRolReposirory;

    public Optional<Rol> getByRolNombre(RolNombre rolNombre) {
        return iRolReposirory.findByRolNombre(rolNombre);
    }

    public void save(Rol rol){
        iRolReposirory.save(rol);
    }
}
