package com.savage.datos;

import com.savage.model.Estudiante;
import com.savage.model.Usuario;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

@ApplicationScoped
public class UsuarioDaoImpl implements UsuarioDao {

    @Inject
    private EntityManager em;

    @Override
    public List<Usuario> findAllUsuario() {
        return em.createNamedQuery("Usuario.findAll").getResultList();
    }

    @Override
    public Usuario findUsuarioByIdUsuario(Estudiante estudiante) {
        return em.find(Usuario.class, estudiante.getIdestudiante());
    }

    @Override
    public Usuario findUsuarioByUsuario(Usuario usuario) {
        return (Usuario) em.createNamedQuery("Usuario.findByUsuario").setParameter("usuario", usuario.getUsuario()).getSingleResult();
    }

    @Override
    public void insertUsuario(Usuario usuario) {
        em.getTransaction().begin();
        em.persist(usuario);
        em.getTransaction().commit();
    }

    @Override
    public void updateUsuario(Usuario usuario) {
        em.getTransaction().begin();
        em.merge(usuario);
        em.getTransaction().commit();
    }

    @Override
    public void deleteUsuario(Usuario usuario) {
        em.getTransaction().begin();
        em.remove(em.merge(usuario));
        em.getTransaction().commit();
    }

    @Override
    public Usuario iniciarSesion(Usuario us) {
        Usuario usuario = null;
        String consulta;
        try {
            consulta = "FROM Usuario u WHERE u.usuario = ?1 and u.clave = ?2";
            Query query = em.createQuery(consulta);
            query.setParameter(1, us.getUsuario());
            query.setParameter(2, us.getClave());

            List<Usuario> lista = query.getResultList();
            if (!lista.isEmpty()) {
                usuario = lista.get(0);
            }
        } catch (Exception e) {
            throw e;
        }
        return usuario;
    }
}
