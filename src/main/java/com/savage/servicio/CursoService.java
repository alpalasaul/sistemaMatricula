
package com.savage.servicio;

import com.savage.model.Curso;
import java.util.List;

public interface CursoService {
    
    public List<Curso> listarCurso();
    
    public List<Curso> listarCursoEstado();
    
    public Curso encontrarCursoPorIdCurso(Curso curso);
    
    public void registrarCurso(Curso curso);
    
    public void modificarCurso(Curso curso);
    
    public void eliminarCurso(Curso curso);
}
