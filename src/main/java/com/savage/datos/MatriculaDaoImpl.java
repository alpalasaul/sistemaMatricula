package com.savage.datos;

import com.savage.model.Matricula;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

@ApplicationScoped
public class MatriculaDaoImpl implements MatriculaDao {

    @Inject
    private EntityManager em;

    @Override
    public List<Matricula> findAll() {
        return em.createNamedQuery("Matricula.findAll").getResultList();
    }

    @Override
    public Matricula findMatriculaByIdmatricula(Matricula matricula) {
        return em.find(Matricula.class, matricula.getIdmatricula());
    }

    @Override
    public void insertMatricula(Matricula matricula) {
        em.getTransaction().begin();
        em.persist(matricula);
        em.getTransaction().commit();
    }

    @Override
    public void updateMatricula(Matricula matricula) {
        em.getTransaction().begin();
        em.merge(matricula);
        em.getTransaction().commit();
    }

    @Override
    public void deleteMatricula(Matricula matricula) {
        em.getTransaction().begin();
        em.remove(em.merge(matricula));
        em.getTransaction().commit();
    }

    @Override
    public List<Matricula> buscarMatricula(int codigoEstudiante) {
        List<Matricula> lista = null;
        String consulta;
        try {
            consulta = "FROM Matricula m WHERE m.estudiante.idestudiante = ?1";
            Query query = em.createQuery(consulta);
            query.setParameter(1, codigoEstudiante);

            lista = query.getResultList();
        } catch (Exception e) {
            throw e;
        }
        return lista;
    }

}
