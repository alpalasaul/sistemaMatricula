
package com.savage.datos;

import com.savage.model.Estudiante;
import com.savage.model.Usuario;
import java.util.List;

public interface UsuarioDao {
    
    public List<Usuario> findAllUsuario();
    
    public Usuario findUsuarioByIdUsuario(Estudiante estudiante);
    
    public Usuario findUsuarioByUsuario(Usuario usuario);
    
    public void insertUsuario(Usuario usuario);
        
    public void updateUsuario(Usuario usuario);
    
    public void deleteUsuario(Usuario usuario);
    
    Usuario iniciarSesion(Usuario us);
    
    
}
