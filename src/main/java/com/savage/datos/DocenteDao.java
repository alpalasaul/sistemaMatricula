
package com.savage.datos;

import com.savage.model.Docente;
import java.util.List;

public interface DocenteDao {
    
    public List<Docente> findAllDocente();
    
    public List<Docente> findAllDocenteEstado();
    
    public Docente findDocenteByIdDocente(Docente docente);
    
    public Docente findDocenteByCedula(Docente docente);
    
    public void insertDocente(Docente docente);
    
    public void updateDocente(Docente docente);
    
    public void deleteDocente(Docente docente);
    
    
}
