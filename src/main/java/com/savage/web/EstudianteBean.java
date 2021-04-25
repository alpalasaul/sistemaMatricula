package com.savage.web;

import com.savage.model.Estudiante;
import com.savage.servicio.EstudianteService;
import java.io.Serializable;
import java.util.List;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
//import javax.enterprise.context.RequestScoped;
import javax.faces.view.ViewScoped;
import javax.inject.*;
import org.primefaces.event.RowEditEvent;
import org.primefaces.util.LangUtils;

@Named("estudianteBean")
@ViewScoped
//@RequestScoped
public class EstudianteBean implements Serializable {


    @Inject
    private EstudianteService estudianteService;

    @Inject
    private Estudiante estudianteSeleccionado;
    
    private List<Estudiante> estudiantes;
    private List<Estudiante> estudiantesFiltrados;

    public EstudianteBean() {
    }

    @PostConstruct
    public void inicializar() {
        //Inicializara cada elemento de la persona Bean 
        //Iniciamos las variables
        this.estudiantes = estudianteService.listarEstudiante();
        //estudianteSeleccionado= new Estudiante();
    }

    public void editListener(RowEditEvent event) {
        Estudiante estudiante = (Estudiante) event.getObject();
        estudianteService.modificarEstudiante(estudiante);
    }

    public Estudiante getEstudianteSeleccionado() {
        return estudianteSeleccionado;
    }

    public void setEstudianteSeleccionado(Estudiante estudianteSeleccionado) {
        this.estudianteSeleccionado = estudianteSeleccionado;
    }

    public List<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(List<Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
    }

    public List<Estudiante> getEstudiantesFiltrados() {
        return estudiantesFiltrados;
    }

    public void setEstudiantesFiltrados(List<Estudiante> estudiantesFiltrados) {
        this.estudiantesFiltrados = estudiantesFiltrados;
    }

    public void agregarEstudiante() {
        try {
            this.estudianteService.registrarEstudiante(estudianteSeleccionado);
            this.estudiantes.add(estudianteSeleccionado);
            this.estudianteSeleccionado = new Estudiante();
        } catch (Exception e) {
            // mensaje de JSF
        }
    }

    public void eliminarEstudiante() {
        this.estudianteService.eliminarEstudiante(estudianteSeleccionado);
        this.estudiantes.remove(estudianteSeleccionado);
        this.estudianteSeleccionado = new Estudiante();
    }

    public void reiniciarEstudianteSeleccionado() {
        this.estudianteSeleccionado = new Estudiante();
    }
    
    public Estudiante buscarEstudianteCedula(Estudiante estudiante) {
        return this.estudianteService.encontrarEstudiantePorId(estudiante);
    }
    
    public void actualizarEstudiante(Estudiante estu) {
        try {
            this.estudianteService.modificarEstudiante(estu);
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

        Estudiante estudiantes1 = (Estudiante) value;
        return estudiantes1.getNombre().toLowerCase().contains(filterText)
                || estudiantes1.getApellido().toLowerCase().contains(filterText)
                || estudiantes1.getIdestudiante() == filterInt
                || estudiantes1.getTelefono().toLowerCase().contains(filterText)
                || estudiantes1.getEmail().toLowerCase().contains(filterText)
                || estudiantes1.getCedula().toLowerCase().contains(filterText);
    }

    private int getInteger(String string) {
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException ex) {
            return 0;
        }
    }
    
    public String estadoTxt(Estudiante estu) {
        String nombre;
        if (estu.getEstado() == 1) {
            nombre = "Activo";
        } else {
            nombre = "Inactivo";
        }
        return nombre;
    }
    

}
