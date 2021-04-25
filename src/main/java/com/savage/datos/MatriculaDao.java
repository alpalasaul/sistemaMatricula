package com.savage.datos;

import com.savage.model.Matricula;
import java.util.List;



public interface MatriculaDao {

    public List<Matricula> findAll();

    public Matricula findMatriculaByIdmatricula(Matricula matricula);

    public void insertMatricula(Matricula matricula);

    public void updateMatricula(Matricula matricula);

    public void deleteMatricula(Matricula matricula);
    
    List<Matricula> buscarMatricula(int codigoEstudiante) throws Exception;

}
