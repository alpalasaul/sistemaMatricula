package com.savage.datos;

import com.savage.model.Nivel;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@ApplicationScoped
public class NivelDaoImpl implements NivelDao {

    @Inject
    private EntityManager em;

    @Override
    public List<Nivel> findAllNivel() {
        return em.createNamedQuery("Nivel.findAll").getResultList();
    }

    @Override
    public List<Nivel> findAllNivelEstado() {
        return em.createNamedQuery("Nivel.findAllEstado").getResultList();
    }

    @Override
    public Nivel findNivelByIdnivel(Nivel nivel) {
        return em.find(Nivel.class, nivel.getIdnivel());
    }

    @Override
    public void insertNivel(Nivel nivel) {
        em.getTransaction().begin();
        em.persist(nivel);
        em.getTransaction().commit();
    }

    @Override
    public void updateNivel(Nivel nivel) {
        em.getTransaction().begin();
        em.merge(nivel);
        em.getTransaction().commit();
    }

    @Override
    public void deleteNivel(Nivel nivel) {
        em.getTransaction().begin();
        em.remove(em.merge(nivel));
        em.getTransaction().commit();
    }

}
