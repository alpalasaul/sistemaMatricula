package com.savage.datos;

import com.savage.model.Docente;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@ApplicationScoped
public class DocenteDaoImpl implements DocenteDao {

    @Inject
    private EntityManager em;

    @Override
    public List<Docente> findAllDocente() {
        return em.createNamedQuery("Docente.findAll").getResultList();
    }

    @Override
    public List<Docente> findAllDocenteEstado() {
        return em.createNamedQuery("Docente.findAllEstado").getResultList();
    }

    @Override
    public Docente findDocenteByIdDocente(Docente docente) {
        return em.find(Docente.class, docente.getIdDocente());
    }

    @Override
    public Docente findDocenteByCedula(Docente docente) {
        return em.find(Docente.class, docente.getCedula());
    }

    @Override
    public void insertDocente(Docente docente) {
        em.getTransaction().begin();
        em.persist(docente);
        em.getTransaction().commit();

    }

    @Override
    public void updateDocente(Docente docente) {
        em.getTransaction().begin();
        em.merge(docente);
        em.getTransaction().commit();
    }

    @Override
    public void deleteDocente(Docente docente) {
        em.getTransaction().begin();
        em.remove(em.merge(docente));
        em.getTransaction().commit();
    }

}
