
package com.savage.datos;

import com.savage.model.Estudiante;
import java.util.List;

public interface EstudianteDao {
    
    public List<Estudiante> findAllEstudiante();
    
    public List<Estudiante> findAllEstudianteEstado();
    
    public Estudiante findEstudianteByIdestudiante(Estudiante estudiante);
    
    public Estudiante findEstudianteByEmail(Estudiante estudiante);
    
    public Estudiante findEstudianteByCedula(Estudiante estudiante);
    
    public void insertEstudiante(Estudiante estudiante);
    
    public void updateEstudiante(Estudiante estudiante);
    
    public void deleteEstudiante(Estudiante estudiante);
}
