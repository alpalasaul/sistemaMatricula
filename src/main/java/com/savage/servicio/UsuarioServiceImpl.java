package com.savage.servicio;

import com.savage.datos.UsuarioDao;
import com.savage.model.Estudiante;
import com.savage.model.Usuario;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class UsuarioServiceImpl implements UsuarioService {

    @Inject
    private UsuarioDao usuarioDao;

    @Override
    public List<Usuario> listarUsuario() {
        return usuarioDao.findAllUsuario();
    }
    
    @Override
    public Usuario encontrarUsuarioPorId(Estudiante estudiante) {
        return usuarioDao.findUsuarioByIdUsuario(estudiante);
    }
        
    @Override
    public Usuario encontrarUsuarioPorUsuario(Usuario usuario) {
        return usuarioDao.findUsuarioByUsuario(usuario);
    }
    
    @Override
    public void registrarUsuario(Usuario usuario) {
        usuarioDao.insertUsuario(usuario);
    }

    @Override
    public void modificarUsuario(Usuario usuario) {
        
            usuarioDao.updateUsuario(usuario);
        
    }

    @Override
    public void eliminarUsuario(Usuario usario) {
        usuarioDao.deleteUsuario(usario);
    }

    @Override
    public Usuario iniciarSesion(Usuario us) {
       
        return usuarioDao.iniciarSesion(us);
    }

}
