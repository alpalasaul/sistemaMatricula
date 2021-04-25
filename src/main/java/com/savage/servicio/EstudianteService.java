
package com.savage.servicio;

import com.savage.model.Estudiante;
import java.util.List;

public interface EstudianteService {
    
    public List<Estudiante> listarEstudiante();
    
    public List<Estudiante> listarEstudianteEstado();
    
    public Estudiante encontrarEstudiantePorId(Estudiante estudiante);
    
    public Estudiante encontrarEstudiantePorEmail(Estudiante estudiante);
    
    public Estudiante encontrarEstudiantePorCedula(Estudiante estudiante);
    
    public void registrarEstudiante(Estudiante estudiante);
    
    public void modificarEstudiante(Estudiante estudiante);
    
    public void eliminarEstudiante(Estudiante estudiante);
            
}
