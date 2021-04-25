package com.savage.servicio;

import com.savage.model.Matricula;
import java.util.List;

public interface MatriculaService {

    public List<Matricula> listarMatricula();

    public Matricula encontrarMatriculaPorIdmatricula(Matricula matricula);

    public void registrarMatricula(Matricula matricula);

    public void modificarMatricula(Matricula matricula);

    public void eliminarMatricula(Matricula matricula);
    
    List<Matricula> buscarMatricula(int codigoEstudiante) throws Exception;

}
