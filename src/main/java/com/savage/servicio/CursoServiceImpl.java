package com.savage.servicio;

import com.savage.datos.CursoDao;
import com.savage.model.Curso;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class CursoServiceImpl implements CursoService {

    @Inject
    private CursoDao cursoDao;

    @Override
    public List<Curso> listarCurso() {
        return cursoDao.findAllCurso();
    }

    @Override
    public List<Curso> listarCursoEstado() {
        return cursoDao.findAllCursoEstado();
    }

    @Override
    public Curso encontrarCursoPorIdCurso(Curso curso) {
        return cursoDao.findCursoByIdcurso(curso);
    }

    @Override
    public void registrarCurso(Curso curso) {
        cursoDao.insertCurso(curso);
    }

    @Override
    public void modificarCurso(Curso curso) {
        cursoDao.updateCurso(curso);
    }

    @Override
    public void eliminarCurso(Curso curso) {
        cursoDao.deleteCurso(curso);
    }

}
