package com.savage.web;

import com.savage.model.Menu;
import com.savage.model.Usuario;
import com.savage.servicio.MenuService;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
//import javax.enterprise.context.RequestScoped;
//import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

@Named("menuBean")
//@RequestScoped
//@ViewScoped
@SessionScoped
public class MenuBean implements Serializable {
    
    @Inject
    private MenuService menuService;
    
    private List<Menu> lista;
    
    private MenuModel model;

    @PostConstruct
    public void init() {
        this.listarMenus();
        model = new DefaultMenuModel();
        this.entablecerPermisos();
    }
    
    public void listarMenus() {
        try {
            lista = menuService.listarMenu();
        } catch (Exception e) {
            //mensaje jsf
        }
    }
    
    public MenuModel getModel() {
        return model;
    }

    public void setModel(MenuModel model) {
        this.model = model;
    }
    
    public void entablecerPermisos() {
        Usuario us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");

        for (Menu m : lista) {
            if (m.getTipo().equals("S") && m.getTipoUsuario().equals(us.getRol())) {
//                DefaultSubMenu firstSubmenu = new DefaultSubMenu(m.getNombre());
                DefaultSubMenu firstSubmenu = DefaultSubMenu.builder().label(m.getNombre()).build();
                for (Menu i : lista) {
                    Menu submenu = i.getSubmenu();
                    if (submenu != null) {
                        if (submenu.getCodigo() == m.getCodigo()) {
//                            DefaultMenuItem item = new DefaultMenuItem(i.getNombre());
                            DefaultMenuItem item = DefaultMenuItem.builder().value(i.getNombre()).icon("pi pi-fw pi-pencil").build();
                            item.setUrl(i.getUrl());
                            item.setIcon(i.getIcono());
                            firstSubmenu.getElements().add(item);
                        }
                    }
                }
                model.getElements().add(firstSubmenu);
            } else {
                if (m.getSubmenu() == null && m.getTipoUsuario().equals(us.getRol())) {
                    DefaultMenuItem item = DefaultMenuItem.builder().value(m.getNombre()).icon("pi pi-fw pi-pencil").build();
                    item.setUrl(m.getUrl());
                    item.setIcon(m.getIcono());
                    model.getElements().add(item);
                }

            }
        }
    }
    
    
    public void cerrarSesion(){ // cerrara la sesion que tenemos almacenado en nuestro facecontext
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    }
    
    public String mostrarUsuarioLogeado(){
        Usuario us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        return us.getEstudiante_idestudiante().getNombre() + " " + us.getEstudiante_idestudiante().getApellido();
    }
}
