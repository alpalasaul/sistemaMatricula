package com.savage.servicio;

import com.savage.datos.EstudianteDao;
import com.savage.model.Estudiante;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class EstudianteServiceImpl implements EstudianteService {

    @Inject
    private EstudianteDao estudianteDao;

    @Override
    public List<Estudiante> listarEstudiante() {
        return estudianteDao.findAllEstudiante();
    }

    @Override
    public List<Estudiante> listarEstudianteEstado() {
        return estudianteDao.findAllEstudianteEstado();
    }

    @Override
    public Estudiante encontrarEstudiantePorId(Estudiante estudiante) {
        return estudianteDao.findEstudianteByIdestudiante(estudiante);
    }

    @Override
    public Estudiante encontrarEstudiantePorCedula(Estudiante estudiante) {
        return estudianteDao.findEstudianteByCedula(estudiante);
    }

    @Override
    public Estudiante encontrarEstudiantePorEmail(Estudiante estudiante) {
        return estudianteDao.findEstudianteByEmail(estudiante);
    }

    @Override
    public void registrarEstudiante(Estudiante estudiante) {
        estudianteDao.insertEstudiante(estudiante);
    }

    @Override
    public void modificarEstudiante(Estudiante estudiante) {

        estudianteDao.updateEstudiante(estudiante);

    }

    @Override
    public void eliminarEstudiante(Estudiante estudiante) {
        estudianteDao.deleteEstudiante(estudiante);
    }

}
