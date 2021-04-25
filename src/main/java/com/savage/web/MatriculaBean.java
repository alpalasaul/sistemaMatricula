package com.savage.web;

import com.savage.model.Curso;
import com.savage.model.Estudiante;
import com.savage.model.Matricula;
import com.savage.model.Usuario;
import com.savage.servicio.CursoService;
import com.savage.servicio.EstudianteService;
import com.savage.servicio.MatriculaService;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
//import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.RowEditEvent;
import org.primefaces.util.LangUtils;

@Named("matriculaBean")
@ViewScoped
//@RequestScoped
public class MatriculaBean implements Serializable {

    @Inject
    private MatriculaService matriculaService;

    @Inject
    private CursoService cursoService;

    @Inject
    private Matricula matriculaSeleccionada;

    @Inject
    private Curso cursoSeleccionado;

    @Inject
    private Estudiante estudianteSeleccionado;

    @Inject
    private EstudianteService estudianteService;

    @Inject
    private Estudiante estudianteActual;

    @Inject
    private Curso curso;

    private List<Matricula> matricula;
    private List<Matricula> matriculaAll;
    private List<Curso> cursos;
    private List<Estudiante> estudiantes;
    private List<Matricula> matriculasFiltradas;

    public MatriculaBean() {
    }

    @PostConstruct
    public void inicializar() {
        buscarMatricula();
        this.matriculaAll = matriculaService.listarMatricula();
        this.cursos = cursoService.listarCursoEstado();
        this.estudiantes = estudianteService.listarEstudianteEstado();
    }

    public void asignar(Curso curso) {
        this.curso = curso;
    }

    public void editListener(RowEditEvent event) {
        Matricula matriculas = (Matricula) event.getObject();
        matriculaService.modificarMatricula(matriculas);
    }

    public void actualizarMatricula(Matricula mat) {
        try {
            //mat.setEstudiante(estudianteSeleccionado);
            //mat.setCurso(cursoSeleccionado);
            this.matriculaService.modificarMatricula(mat);
            matriculaAll = matriculaService.listarMatricula();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se actualizó con éxito"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "¡Error al actualizar!"));
        }
    }

    public Estudiante getEstudianteActual() {
        return estudianteActual;
    }

    public void setEstudianteActual(Estudiante estudianteActual) {
        this.estudianteActual = estudianteActual;
    }

    public Matricula getMatriculaSeleccionada() {
        return matriculaSeleccionada;
    }

    public void setMatriculaSeleccionada(Matricula matriculaSeleccionada) {
        this.matriculaSeleccionada = matriculaSeleccionada;
    }

    public Curso getCursoSeleccionado() {
        return cursoSeleccionado;
    }

    public void setCursoSeleccionado(Curso cursoSeleccionado) {
        this.cursoSeleccionado = cursoSeleccionado;
    }

    public Estudiante getEstudianteSeleccionado() {
        return estudianteSeleccionado;
    }

    public void setEstudianteSeleccionado(Estudiante estudianteSeleccionado) {
        this.estudianteSeleccionado = estudianteSeleccionado;
    }

    public List<Matricula> getMatricula() {
        return matricula;
    }

    public List<Matricula> getMatriculaAll() {
        return matriculaAll;
    }

    public void setMatriculaAll(List<Matricula> matriculaAll) {
        this.matriculaAll = matriculaAll;
    }

    public void setMatricula(List<Matricula> matricula) {
        this.matricula = matricula;
    }

    public List<Matricula> getMatriculasFiltradas() {
        return matriculasFiltradas;
    }

    public void setMatriculasFiltradas(List<Matricula> matriculasFiltradas) {
        this.matriculasFiltradas = matriculasFiltradas;
    }

    public List<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }

    public List<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(List<Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public void agregarMatricula() {

        Usuario us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");

        try {
            matriculaSeleccionada.setEstudiante(us.getEstudiante_idestudiante());
            matriculaSeleccionada.setCurso(curso);
            matriculaSeleccionada.setFechaMatricula(new Date(Calendar.getInstance().getTimeInMillis()));
            this.matriculaService.registrarMatricula(matriculaSeleccionada);
            this.matricula.add(matriculaSeleccionada);

            if (curso.getCupo() != 0) {
                this.curso.setCupo(this.curso.getCupo() - 1);
                cursoService.modificarCurso(curso);
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se ha matriculado con éxito en un nuevo curso"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "¡Error! No se ha podido matricular"));
        } finally {
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        }
    }

    public void nuevaMatricula() {

        try {
            matriculaSeleccionada.setEstudiante(estudianteService.encontrarEstudiantePorId(estudianteSeleccionado));
            matriculaSeleccionada.setCurso(cursoService.encontrarCursoPorIdCurso(cursoSeleccionado));
            matriculaSeleccionada.setFechaMatricula(new Date(Calendar.getInstance().getTimeInMillis()));
            this.matriculaService.registrarMatricula(matriculaSeleccionada);
            this.matriculaAll.add(matriculaSeleccionada);
            //this.matriculaAll = matriculaService.listarMatricula();           

            if (cursoService.encontrarCursoPorIdCurso(cursoSeleccionado).getCupo() != 0) {
                cursoService.encontrarCursoPorIdCurso(cursoSeleccionado).setCupo(cursoService.encontrarCursoPorIdCurso(cursoSeleccionado).getCupo() - 1);
                cursoService.modificarCurso(cursoService.encontrarCursoPorIdCurso(cursoSeleccionado));
            }

            this.matriculaSeleccionada = new Matricula();
            this.cursoSeleccionado = new Curso();
            this.estudianteSeleccionado = new Estudiante();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se ha matriculado con éxito en un nuevo curso"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "¡Error! No se ha podido matricular"));
        }

    }

    public void eliminarMatricula() {
        this.matriculaService.eliminarMatricula(matriculaSeleccionada);
        this.matricula.remove(matriculaSeleccionada);
        this.matriculaSeleccionada = new Matricula();
    }

    public void reiniciarMatriculaSeleccionada() {
        this.matriculaSeleccionada = new Matricula();
    }

    public void buscarMatricula() {
        Usuario us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        try {
            matricula = matriculaService.buscarMatricula(us.getEstudiante_idestudiante().getIdestudiante());
        } catch (Exception e) {
            //throw e;
        }
    }

    public String estadoTxt(Matricula mat) {
        String nombre;
        if (mat.getEstado() == 1) {
            nombre = "Activo";
        } else {
            nombre = "Inactivo";
        }
        return nombre;
    }

    public String estadoPagoTxt(Matricula mat) {
        String nombre;
        if (mat.getEstadoCobro() == 1) {
            nombre = "Pagado";
        } else {
            nombre = "Sin pagar";
        }
        return nombre;
    }

    public String btnEstado(Matricula mat) {
        String nombre;
        if (mat.getEstadoCobro() == 1) {
            nombre = "Pagado";
        } else {
            nombre = "$ Pagar";
        }
        return nombre;
    }

    public void pagar(Matricula mat) {
        Usuario us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        try {
            estudianteActual = new Estudiante();
            estudianteActual = estudianteService.encontrarEstudiantePorId(us.getEstudiante_idestudiante());
//            if (!us.getEstudiante_idestudiante().getNum_tarjeta().equals("") && us.getEstudiante_idestudiante().getNum_tarjeta() != null) {
            if (!estudianteActual.getNum_tarjeta().equals("")) {
                mat.setEstadoCobro(1);
                this.matriculaService.modificarMatricula(mat);
                matricula = matriculaService.buscarMatricula(us.getEstudiante_idestudiante().getIdestudiante());
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "El pago se realizó con éxito"));
            }
//            if (us.getEstudiante_idestudiante().getNum_tarjeta().equals("") || us.getEstudiante_idestudiante().getNum_tarjeta() == null) {
            if (estudianteActual.getNum_tarjeta().equals("")) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "No cuenta con una tarjeta para realiza el pago"));
            }

        } catch (Exception e) {
            // mensaje de JSF
        }
    }

    public boolean comprobarMatriculaExistente(Curso cur) {
        Usuario us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        boolean bandera = false;
        if (us != null) {
            for (Matricula mat : matricula) {
                if (mat.getCurso().getIdcurso() == cur.getIdcurso()) {
                    bandera = true;
                }
            }
        }
        if (comprobarBtnMatricula(cur).equals("Caducado") || cur.getCupo() == 0) {
            bandera = true;
        }

        return bandera;
    }

    public String comprobarBtnMatricula(Curso cur) {
        Usuario us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        String estadoBtn = "Seguir curso";
        Date fecha = new Date(Calendar.getInstance().getTimeInMillis());

        if (cur.getCupo() == 0) {
            estadoBtn = "Sin cupos";
        }

        if (fecha.after(cur.getFechaFinalizacion())) {
            estadoBtn = "Caducado";
        }

        if (us != null) {
            for (Matricula mat : matricula) {
                if (mat.getCurso().getIdcurso() == cur.getIdcurso()) {
                    estadoBtn = "Matriculado";
                }
            }
        }

        return estadoBtn;
    }

    public String visibleCupos(Curso cur) {
        String clase = "cup";
        Date fecha = new Date(Calendar.getInstance().getTimeInMillis());

        if (cur.getCupo() == 0) {
            clase = "cup1";
        }

        if (fecha.after(cur.getFechaFinalizacion())) {
            clase = "cup2";
        }
        return clase;
    }

    public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
        if (LangUtils.isValueBlank(filterText)) {
            return true;
        }

        int filterInt = getInteger(filterText);

        Matricula mat1 = (Matricula) value;
        return mat1.getIdmatricula() == filterInt
                || mat1.getCurso().getNombre().toLowerCase().contains(filterText)
                || mat1.getEstudiante().getNombre().toLowerCase().contains(filterText)
                || mat1.getEstudiante().getApellido().toLowerCase().contains(filterText)
                || mat1.getCurso().getNombre().toLowerCase().contains(filterText);
    }

    private int getInteger(String string) {
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException ex) {
            return 0;
        }
    }
}
