package com.savage.servicio;

import com.savage.model.Estudiante;
import com.savage.model.Usuario;
import java.util.List;

public interface UsuarioService {

    public List<Usuario> listarUsuario();
    
    public Usuario encontrarUsuarioPorId(Estudiante estudiante);
    
    public Usuario encontrarUsuarioPorUsuario(Usuario usuario);

    public void registrarUsuario(Usuario usuario);

    public void modificarUsuario(Usuario usuario);

    public void eliminarUsuario(Usuario usario);

    Usuario iniciarSesion(Usuario us);

}
