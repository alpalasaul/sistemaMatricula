package com.savage.servicio;

import com.savage.datos.MatriculaDao;
import com.savage.model.Matricula;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class MatriculaServiceImpl implements MatriculaService {

    @Inject
    private MatriculaDao matriculaDao;

    @Override
    public List<Matricula> listarMatricula() {
        return matriculaDao.findAll();
    }

    @Override
    public List<Matricula> buscarMatricula(int codigoEstudiante) throws Exception {
        return matriculaDao.buscarMatricula(codigoEstudiante);
    }

    @Override
    public Matricula encontrarMatriculaPorIdmatricula(Matricula matricula) {
        return matriculaDao.findMatriculaByIdmatricula(matricula);
    }

    @Override
    public void registrarMatricula(Matricula matricula) {
        matriculaDao.insertMatricula(matricula);
    }

    @Override
    public void modificarMatricula(Matricula matricula) {

        matriculaDao.updateMatricula(matricula);

    }

    @Override
    public void eliminarMatricula(Matricula matricula) {
        matriculaDao.deleteMatricula(matricula);
    }

}
