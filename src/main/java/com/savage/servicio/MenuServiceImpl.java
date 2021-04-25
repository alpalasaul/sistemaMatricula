package com.savage.servicio;

import com.savage.datos.MenuDao;
import com.savage.model.Menu;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class MenuServiceImpl implements MenuService {

    @Inject
    private MenuDao menuDao;

    @Override
    public List<Menu> listarMenu() {
        return menuDao.findAllMenu();
    }

    @Override
    public Menu encontrarMenuPorIdMenu(Menu menu) {
        return menuDao.findMenuByIdmenu(menu);
    }

    @Override
    public void registrarMenu(Menu menu) {
        menuDao.insertMenu(menu);
    }

    @Override
    public void modificarMenu(Menu menu) {

        menuDao.updateMenu(menu);

    }

    @Override
    public void eliminarMenu(Menu menu) {
        menuDao.deleteMenu(menu);
    }

}
