package com.savage.datos;

import com.savage.model.Curso;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@ApplicationScoped
public class CursoDaoImpl implements CursoDao {

    @Inject
    private EntityManager em;

    @Override
    public List<Curso> findAllCurso() {
        return em.createNamedQuery("Curso.findAll").getResultList();
    }

    @Override
    public List<Curso> findAllCursoEstado() {
        return em.createNamedQuery("Curso.findAllEstado").getResultList();
    }

    @Override
    public Curso findCursoByIdcurso(Curso curso) {
        return em.find(Curso.class, curso.getIdcurso());
    }

    @Override
    public void insertCurso(Curso curso) {
        em.getTransaction().begin();
        em.persist(curso);
        em.getTransaction().commit();
    }

    @Override
    public void updateCurso(Curso curso) {
        em.getTransaction().begin();
        em.merge(curso);
        em.getTransaction().commit();
    }

    @Override
    public void deleteCurso(Curso curso) {
        em.getTransaction().begin();
        em.remove(em.merge(curso));
        em.getTransaction().commit();
    }

}
