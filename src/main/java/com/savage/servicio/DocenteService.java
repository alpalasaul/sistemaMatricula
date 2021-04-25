
package com.savage.servicio;

import com.savage.model.Docente;
import java.util.List;

public interface DocenteService {
    
    public List<Docente> listarDocente();
    
    public List<Docente> listarDocenteEstado();
    
    public Docente encontrarDocentePorIdDocente(Docente docente);
    
    public Docente encontrarDocentePorCedula(Docente docente);
    
    public void registrarDocente(Docente docente);
    
    public void modificarDocente(Docente docente);
    
    public void eliminarDocente(Docente docente);
}
