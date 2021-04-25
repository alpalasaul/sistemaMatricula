package com.savage.web;

import com.savage.model.Curso;
import com.savage.model.Docente;
import com.savage.model.Nivel;
import com.savage.servicio.CursoService;
import com.savage.servicio.DocenteService;
import com.savage.servicio.NivelService;
import java.io.Serializable;
import java.util.List;
import java.util.Locale;
import javax.annotation.PostConstruct;
//import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.RowEditEvent;
import org.primefaces.util.LangUtils;

@Named("cursoBean")
@ViewScoped
//@RequestScoped
public class CursoBean implements Serializable {

    @Inject
    private CursoService cursoService;
    @Inject
    private NivelService nivelService;
    @Inject
    private DocenteService docenteService;

    @Inject
    private Curso cursoSeleccionado;
    @Inject
    private Nivel nivelSeleccionado;
    @Inject
    private Docente docenteSeleccionado;

    private Curso curso;

    private List<Curso> cursos;
    private List<Curso> cursosDisponibles;
    private List<Docente> docentes;
    private List<Nivel> niveles;
    private List<Curso> cursosFiltrados;

    public CursoBean() {
    }

    @PostConstruct
    public void inicializar() {
        this.cursos = cursoService.listarCurso();
        this.niveles = nivelService.listarNivelEstado();
        this.docentes = docenteService.listarDocenteEstado();
        this.cursosDisponibles = cursoService.listarCursoEstado();  
    }

    public void editListener(RowEditEvent event) {
        Curso curso1 = (Curso) event.getObject();
        cursoService.modificarCurso(curso1);
    }

    public Curso getCursoSeleccionado() {
        return cursoSeleccionado;
    }

    public void setCursoSeleccionado(Curso cursoSeleccionado) {
        this.cursoSeleccionado = cursoSeleccionado;
    }

    public Nivel getNivelSeleccionado() {
        return nivelSeleccionado;
    }

    public void setNivelSeleccionado(Nivel nivelSeleccionado) {
        this.nivelSeleccionado = nivelSeleccionado;
    }

    public Docente getDocenteSeleccionado() {
        return docenteSeleccionado;
    }

    public void setDocenteSeleccionado(Docente docenteSeleccionado) {
        this.docenteSeleccionado = docenteSeleccionado;
    }

    public List<Curso> getCursosDisponibles() {
        return cursosDisponibles;
    }

    public void setCursosDisponibles(List<Curso> cursosDisponibles) {
        this.cursosDisponibles = cursosDisponibles;
    }

    public List<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }

    public List<Docente> getDocentes() {
        return docentes;
    }

    public void setDocentes(List<Docente> docentes) {
        this.docentes = docentes;
    }

    public List<Nivel> getNiveles() {
        return niveles;
    }

    public void setNiveles(List<Nivel> niveles) {
        this.niveles = niveles;
    }

    public List<Curso> getCursosFiltrados() {
        return cursosFiltrados;
    }

    public void setCursosFiltrados(List<Curso> cursosFiltrados) {
        this.cursosFiltrados = cursosFiltrados;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public void agregarCurso() {

        try {
            cursoSeleccionado.setNivel_idnivel(nivelService.encontrarNivelPorIdnivel(nivelSeleccionado));
            cursoSeleccionado.setDocente_iddocente(docenteService.encontrarDocentePorIdDocente(docenteSeleccionado));
            cursoService.registrarCurso(cursoSeleccionado);
            this.cursos.add(cursoSeleccionado);
            this.cursoSeleccionado = new Curso();
            this.docenteSeleccionado = new Docente();
            this.nivelSeleccionado = new Nivel();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "¡Se registró con éxito!"));

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso", "¡Error al registrar! Compruebe que los datos ingresados son correctos")); //mensaje de JSF

        }
    }

    public void eliminarCurso() {
        this.cursoService.eliminarCurso(cursoSeleccionado);
        this.cursos.remove(cursoSeleccionado);
        this.cursoSeleccionado = new Curso();
    }

    public void reiniciarCursoSeleccionado() {
        this.cursoSeleccionado = new Curso();
    }

    public void actualizarCurso(Curso cur) {
        //Usuario us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        try {
            cur.setDocente_iddocente(docenteSeleccionado);
            cur.setNivel_idnivel(nivelSeleccionado);
            this.cursoService.modificarCurso(cur);
            this.cursos = cursoService.listarCurso();
            //if (us.getRol().equals("A")) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se actualizó con éxito"));
            //}
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "¡Error al actualizar!"));
        }
    }

    public void asignar(Curso curso) {
        this.curso = curso;
    }

    public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
        if (LangUtils.isValueBlank(filterText)) {
            return true;
        }

        int filterInt = getInteger(filterText);

        Curso curso1 = (Curso) value;
        return curso1.getNombre().toLowerCase().contains(filterText)
                || curso1.getIdcurso() == filterInt
                || curso1.getDocente_iddocente().getNombre().toLowerCase().contains(filterText)
                || curso1.getDocente_iddocente().getApellido().toLowerCase().contains(filterText)
                || curso1.getNivel_idnivel().getNombre().toLowerCase().contains(filterText)
                || curso1.getDescripcion().toLowerCase().contains(filterText);
//                || curso1.getCupo() == filterInt;
    }

    private int getInteger(String string) {
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException ex) {
            return 0;
        }
    }

    public String estadoTxt(Curso cur) {
        String nombre;
        if (cur.getEstado() == 1) {
            nombre = "Activo";
        } else {
            nombre = "Inactivo";
        }
        return nombre;
    }

}
