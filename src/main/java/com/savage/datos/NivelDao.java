
package com.savage.datos;

import com.savage.model.Nivel;
import java.util.List;

public interface NivelDao {
    
    public List<Nivel> findAllNivel();
    
    public List<Nivel> findAllNivelEstado();
    
    public Nivel findNivelByIdnivel(Nivel nivel);
    
    public void insertNivel(Nivel nivel);
    
    public void updateNivel(Nivel nivel);
    
    public void deleteNivel(Nivel nivel);
    
    
}
