package com.savage.servicio;

import com.savage.model.Menu;
import java.util.List;

public interface MenuService {

    public List<Menu> listarMenu();

    public Menu encontrarMenuPorIdMenu(Menu menu);

    public void registrarMenu(Menu menu);

    public void modificarMenu(Menu menu);

    public void eliminarMenu(Menu menu);
}
