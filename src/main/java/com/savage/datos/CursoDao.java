
package com.savage.datos;

import java.util.List;
import  com.savage.model.Curso;

public interface CursoDao {
    
    public List<Curso> findAllCurso();
    
    public List<Curso> findAllCursoEstado();
    
    public Curso findCursoByIdcurso(Curso curso);
    
    public void insertCurso(Curso curso);
    
    public void updateCurso(Curso curso);
    
    public void deleteCurso(Curso curso);
}
