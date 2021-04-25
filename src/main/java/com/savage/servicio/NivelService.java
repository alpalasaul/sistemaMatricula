
package com.savage.servicio;

import com.savage.model.Nivel;
import java.util.List;

public interface NivelService {
    
    public List<Nivel> listarNivel();
    
    public List<Nivel> listarNivelEstado();
    
    public Nivel encontrarNivelPorIdnivel(Nivel nivel);
    
    public void registrarNivel(Nivel nivel);
    
    public void modificarNivel(Nivel nivel);
    
    public void eliminarNivel(Nivel nivel);
}
