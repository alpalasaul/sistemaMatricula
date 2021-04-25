package com.savage.datos;

import com.savage.model.Estudiante;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@ApplicationScoped
public class EstudianteDaoImpl implements EstudianteDao {

    @Inject
    private EntityManager em;

    @Override
    public List<Estudiante> findAllEstudiante() {
        return em.createNamedQuery("Estudiante.findAll").getResultList();
    }

    @Override
    public List<Estudiante> findAllEstudianteEstado() {
        return em.createNamedQuery("Estudiante.findAllEstado").getResultList();
    }

    @Override
    public Estudiante findEstudianteByIdestudiante(Estudiante estudiante) {
        return em.find(Estudiante.class, estudiante.getIdestudiante());
    }

    @Override
    public Estudiante findEstudianteByCedula(Estudiante estudiante) {
        //return em.find(Estudiante.class, estudiante.getCedula());
        return (Estudiante) em.createNamedQuery("Estudiante.findByCedula").setParameter("cedula", estudiante.getCedula()).getSingleResult();
    }

    @Override
    public Estudiante findEstudianteByEmail(Estudiante estudiante) {
        return em.find(Estudiante.class, estudiante.getEmail());
    }

    @Override
    public void insertEstudiante(Estudiante estudiante) {
        em.getTransaction().begin();
        em.persist(estudiante);
        em.getTransaction().commit();
    }

    @Override
    public void updateEstudiante(Estudiante estudiante) {
        em.getTransaction().begin();
        em.merge(estudiante);
        em.getTransaction().commit();
    }

    @Override
    public void deleteEstudiante(Estudiante estudiante) {
        em.getTransaction().begin();
        em.remove(em.merge(estudiante));
        em.getTransaction().commit();
    }

}
