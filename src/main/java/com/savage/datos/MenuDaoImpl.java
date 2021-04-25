package com.savage.datos;

import com.savage.model.Menu;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@ApplicationScoped
public class MenuDaoImpl implements MenuDao {

    @Inject
    private EntityManager em;

    @Override
    public List<Menu> findAllMenu() {
        return em.createNamedQuery("Menu.findAll").getResultList();
    }

    @Override
    public Menu findMenuByIdmenu(Menu menu) {
        return em.find(Menu.class, menu.getCodigo());
    }

    @Override
    public void insertMenu(Menu menu) {
        em.getTransaction().begin();
        em.persist(menu);
        em.getTransaction().commit();
    }

    @Override
    public void updateMenu(Menu menu) {
        em.getTransaction().begin();
        em.merge(menu);
        em.getTransaction().commit();
    }

    @Override
    public void deleteMenu(Menu menu) {
        em.getTransaction().begin();
        em.remove(em.merge(menu));
        em.getTransaction().commit();
    }

}
