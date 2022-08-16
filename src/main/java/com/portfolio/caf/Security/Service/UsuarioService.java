package com.portfolio.caf.Security.Service;

import com.portfolio.caf.Security.Entity.Usuario;
import com.portfolio.caf.Security.Repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
@Service
@Transactional
public class UsuarioService {

    @Autowired
    IUsuarioRepository iUsuarioRepository;

    public Optional<Usuario> getByNombreUsuario(String nombreUsuario){
        return iUsuarioRepository.findByNombreUsuario(nombreUsuario);
    }

    public boolean existsByNombreUsuario(String nombreUsuario){
        return iUsuarioRepository.existsByNombreUsuario(nombreUsuario);
    }

    public boolean existsByEmail(String email){
        return iUsuarioRepository.existsByEmail(email);
    }

    public void save(Usuario usuario){
        iUsuarioRepository.save(usuario);
    }

}
