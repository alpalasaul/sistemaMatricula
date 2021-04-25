package com.savage.web;

import com.savage.model.Docente;
import com.savage.servicio.DocenteService;
import java.io.Serializable;
import java.util.List;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
// import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.RowEditEvent;
import org.primefaces.util.LangUtils;

@Named("docenteBean")
// @RequestScoped
@ViewScoped
public class DocenteBean implements Serializable {

    @Inject
    private DocenteService docenteService;

    @Inject
    private Docente docenteSeleccionado;

    private List<Docente> docentesFiltrados;
    private List<Docente> docentes;

    public DocenteBean() {
    }

    @PostConstruct
    public void inicializar() {
        this.docentes = docenteService.listarDocente();
        // docenteSeleccionado = new Docente();
    }

    public void editListener(RowEditEvent event) {
        Docente docente = (Docente) event.getObject();
        docenteService.modificarDocente(docente);
    }

    public Docente getDocenteSeleccionado() {
        return docenteSeleccionado;
    }

    public void setDocenteSeleccionado(Docente docenteSeleccionado) {
        this.docenteSeleccionado = docenteSeleccionado;
    }

    public List<Docente> getDocentes() {
        return docentes;
    }

    public void setDocentes(List<Docente> docentes) {
        this.docentes = docentes;
    }

    public List<Docente> getDocentesFiltrados() {
        return docentesFiltrados;
    }

    public void setDocentesFiltrados(List<Docente> docentesFiltrados) {
        this.docentesFiltrados = docentesFiltrados;
    }

    public void agregarDocente() {
        try {
            this.docenteService.registrarDocente(docenteSeleccionado);
            this.docentes.add(docenteSeleccionado);
            this.docenteSeleccionado = new Docente();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se registró con éxito"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso", "¡Error al registrar! Compruebe que los datos ingresados son correctos")); //mensaje de JSF

        }
    }

    public void eliminarDocente() {
        this.docenteService.eliminarDocente(docenteSeleccionado);
        this.docentes.remove(docenteSeleccionado);
        this.docenteSeleccionado = new Docente();
    }

    public void reiniciarDocenteSeleccionado() {
        this.docenteSeleccionado = new Docente();
    }

    public void actualizarDocente(Docente docen) {
        try {
            this.docenteService.modificarDocente(docen);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se actualizó con éxito"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "¡Error al actualizar!"));
        }
    }

    public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
        if (LangUtils.isValueBlank(filterText)) {
            return true;
        }

        int filterInt = getInteger(filterText);

        Docente docente1 = (Docente) value;
        return docente1.getNombre().toLowerCase().contains(filterText)
                || docente1.getApellido().toLowerCase().contains(filterText)
                || docente1.getIdDocente() == filterInt
                || docente1.getTelefono().toLowerCase().contains(filterText)
                || docente1.getEmail().toLowerCase().contains(filterText)
                || docente1.getCedula().toLowerCase().contains(filterText);
    }

    private int getInteger(String string) {
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException ex) {
            return 0;
        }
    }

    public String estadoTxt(Docente docen) {
        String nombre;
        if (docen.getEstado() == 1) {
            nombre = "Activo";
        } else {
            nombre = "Inactivo";
        }
        return nombre;
    }

}
