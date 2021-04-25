
package com.savage.datos;

import java.util.List;
import  com.savage.model.Menu;

public interface MenuDao {
    
    public List<Menu> findAllMenu();
    
    public Menu findMenuByIdmenu(Menu menu);
    
    public void insertMenu(Menu menu);
    
    public void updateMenu(Menu menu);
    
    public void deleteMenu(Menu menu);
}
