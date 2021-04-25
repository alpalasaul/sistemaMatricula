package com.savage.web;

import com.savage.datos.NivelDao;
import com.savage.model.Nivel;
import com.savage.servicio.NivelService;
import java.io.Serializable;
import java.util.List;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
//import javax.enterprise.context.RequestScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.RowEditEvent;
import org.primefaces.util.LangUtils;

@Named("nivelBean")
//@RequestScoped
@ViewScoped
public class NivelBean implements Serializable {

    @Inject
    private NivelService nivelService; 
    
    @Inject
    private NivelDao nivelDao;

    @Inject
    private Nivel niveleSeleccionado; 

    private Nivel nivelSelect;

    private List<Nivel> nivel;
    private List<Nivel> nivelesFiltrados;

    public NivelBean() {
    }

    @PostConstruct
    public void inicializar() {
        //this.nivel = nivelService.listarNivel();
        this.nivel = nivelDao.findAllNivel();
        //niveleSeleccionado = new Nivel();
        System.out.println("hola");
    }

    public List<Nivel> getNivelesFiltrados() {
        return nivelesFiltrados;
    }

    public void setNivelesFiltrados(List<Nivel> nivelesFiltrados) {
        this.nivelesFiltrados = nivelesFiltrados;
    }

    public void editListener(RowEditEvent event) {
        Nivel niveles = (Nivel) event.getObject();
        nivelService.modificarNivel(niveles);
    }

    public Nivel getNiveleSeleccionado() {
        return niveleSeleccionado;
    }

    public void setNiveleSeleccionado(Nivel niveleSeleccionado) {
        this.niveleSeleccionado = niveleSeleccionado;
    }

    public List<Nivel> getNivel() {
        return nivel;
    }

    public void setNivel(List<Nivel> nivel) {
        this.nivel = nivel;
    }

    public Nivel getNivelSelect() {
        return nivelSelect;
    }

    public void setNivelSelect(Nivel nivelSelect) {
        this.nivelSelect = nivelSelect;
    }

    public void agregarNivel() {
//        try {
            //this.nivelService.registrarNivel(niveleSeleccionado); // lo guarda en la DB
            this.nivelDao.insertNivel(niveleSeleccionado);
            this.nivel.add(niveleSeleccionado); // agrega a la lista
            this.niveleSeleccionado = new Nivel(); // nula
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se registró con éxito"));
//        } catch (Exception e) {
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "¡Error al registrar!"));
//        }
    }

    public void actualizarNivel(Nivel niv) {
        try {
            this.nivelService.modificarNivel(niv);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se actualizó con éxito"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "¡Error al actualizar!"));

        }
    }

    public void eliminarNivel() {
        this.nivelService.eliminarNivel(niveleSeleccionado);
        this.nivel.remove(niveleSeleccionado);
        this.niveleSeleccionado = new Nivel();
    }

    public void reiniciarNivelSeleccionado() {
        this.niveleSeleccionado = new Nivel();
    }

    public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
        if (LangUtils.isValueBlank(filterText)) {
            return true;
        }

        int filterInt = getInteger(filterText);
        double filterDouble = getDouble(filterText);

        Nivel niveles = (Nivel) value;
        return niveles.getNombre().toLowerCase().contains(filterText)
                || niveles.getIdnivel() == filterInt
                || (int) niveles.getPrecio() == filterInt
                || niveles.getPrecio() == filterDouble
                || String.valueOf(niveles.getPrecio()).toLowerCase().contains(filterText);
    }

    private int getInteger(String string) {
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException ex) {
            return 0;
        }
    }

    private double getDouble(String string) {
        try {
            return Double.parseDouble(string);
        } catch (NumberFormatException ex) {
            return 0;
        }
    }

    public String estadoTxt(Nivel niv) {
        String nombre;
        if (niv.getEstado() == 1) {
            nombre = "Activo";
        } else {
            nombre = "Inactivo";
        }
        return nombre;
    }
}
