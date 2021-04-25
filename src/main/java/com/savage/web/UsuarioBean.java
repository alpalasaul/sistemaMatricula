package com.savage.web;

import com.savage.model.Estudiante;
import com.savage.model.Usuario;
import com.savage.servicio.EstudianteService;
import com.savage.servicio.UsuarioService;
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

@Named("usuarioBean")
//@RequestScoped
@ViewScoped
public class UsuarioBean implements Serializable {

    @Inject
    private UsuarioService usuarioService;

    @Inject
    private EstudianteService estudianteService;

    @Inject
    private Usuario usuarioSeleccionado;

    @Inject
    private Estudiante estudianteSeleccionado;
    
    @Inject 
    private Usuario nuevoUsuario;
    
    private List<Usuario> usuario;
    private List<Estudiante> estudiantes;

    private List<Estudiante> estudiantesFiltrados;
    private List<Usuario> usuariosFiltrados;

    public UsuarioBean() {
    }

    @PostConstruct
    public void inicializar() {
        this.usuario = usuarioService.listarUsuario();
        this.estudiantes = estudianteService.listarEstudiante();
        //usuarioSeleccionado = new Usuario();
    }

    public void editListener(RowEditEvent event) {
        Usuario usuarios = (Usuario) event.getObject();
        usuarioService.modificarUsuario(usuarios);
    }

    public Usuario getUsuarioSeleccionado() {
        return usuarioSeleccionado;
    }

    public void setUsuarioSeleccionado(Usuario usuarioSeleccionado) {
        this.usuarioSeleccionado = usuarioSeleccionado;
    }

    public Estudiante getEstudianteSeleccionado() {
        return estudianteSeleccionado;
    }

    public void setEstudianteSeleccionado(Estudiante estudianteSeleccionado) {
        this.estudianteSeleccionado = estudianteSeleccionado;
    }

    public Usuario getNuevoUsuario() {
        return nuevoUsuario;
    }

    public void setNuevoUsuario(Usuario nuevoUsuario) {
        this.nuevoUsuario = nuevoUsuario;
    }

    public List<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(List<Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
    }

    public List<Usuario> getUsuario() {
        return usuario;
    }

    public void setUsuario(List<Usuario> usuario) {
        this.usuario = usuario;
    }

    public List<Estudiante> getEstudiantesFiltrados() {
        return estudiantesFiltrados;
    }

    public void setEstudiantesFiltrados(List<Estudiante> estudiantesFiltrados) {
        this.estudiantesFiltrados = estudiantesFiltrados;
    }

    public List<Usuario> getUsuariosFiltrados() {
        return usuariosFiltrados;
    }

    public void setUsuariosFiltrados(List<Usuario> usuariosFiltrados) {
        this.usuariosFiltrados = usuariosFiltrados;
    }

    public void actualizarEstudiante(Estudiante estu) { // posiblemente cambiar para que edite con 2 parametros us / estu
        try {
            this.estudianteService.modificarEstudiante(estu);
        } catch (Exception e) {
            // mensaje de JSF
        }
    }

    public void actualizarUsuario(Usuario us) { // posiblemente cambiar para que edite con 2 parametros us / estu
        try {
            this.usuarioService.modificarUsuario(us);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se actualizó con éxito"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "¡Error al actualizar!"));
        }
    }

    public void actualizarPerfil() {
        try {
            this.estudianteService.modificarEstudiante(estudianteSeleccionado);
            if (!usuarioSeleccionado.getClave().equals("")) {
                this.usuarioService.modificarUsuario(usuarioSeleccionado);
            } 
            if (!nuevoUsuario.getClave().equals("")) {                
                usuarioSeleccionado.setClave(nuevoUsuario.getClave());
                this.usuarioService.modificarUsuario(usuarioSeleccionado);
            }
            estudianteSeleccionado = new Estudiante();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "¡Datos actualizados!"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "¡Error al actualizar datos!"));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "El nombre de usuario o cédula ingresados ya se encuentran registrados. "));
        }
    }

    public void buscarEstudiantePerfil() {
        Usuario us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        estudianteSeleccionado = estudianteService.encontrarEstudiantePorId(us.getEstudiante_idestudiante());
        usuarioSeleccionado = usuarioService.encontrarUsuarioPorId(estudianteSeleccionado);
    }

    public void agregarUsuario() {

        try {
            this.usuarioSeleccionado.setEstudiante_idestudiante(estudianteSeleccionado);
            this.usuarioService.registrarUsuario(usuarioSeleccionado);
            this.usuario.add(usuarioSeleccionado);
            // aqui se debe agregar a la lista estudiantes para que se refleje en la tabla
            this.estudiantes.add(estudianteSeleccionado);
            this.usuarioSeleccionado = new Usuario();
            this.estudianteSeleccionado = new Estudiante();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "¡Se registró con éxito!"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "¡Error al registrar! comprueba que los datos ingresados son correctos"));
            cedulaExistente();
            usuarioExistente();
        }
    }

    public void eliminarUsuario() {
        this.usuarioService.eliminarUsuario(usuarioSeleccionado);
        this.usuario.remove(usuarioSeleccionado);
        this.usuarioSeleccionado = new Usuario();
    }

    public void reiniciarUsuarioSeleccionado() {
        this.usuarioSeleccionado = new Usuario();
    }

    public String iniciarSesion() {
        Usuario us;
        String redireccion = null;
        try {
            us = usuarioService.iniciarSesion(usuarioSeleccionado);
            if (us != null && us.getRol().equals("E")) {
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", us);
                redireccion = "/protegido/estudiante/misCursos?faces-redirect=true";
            } else if (us != null && us.getRol().equals("A")) {
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", us);
                redireccion = "/protegido/admin/bienvenida?faces-redirect=true";
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "Credenciales incorrectas"));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "¡Error!"));

        }
        return redireccion;
    }

    public void cedulaExistente() {
        try {
            if (estudianteService.encontrarEstudiantePorCedula(estudianteSeleccionado).getCedula().equals(estudianteSeleccionado.getCedula())) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "La cédula ingresada ya existe"));
            }
        } catch (Exception e) {
            // Log
        }
    }

    public void usuarioExistente() {

        try {
            if (usuarioService.encontrarUsuarioPorUsuario(usuarioSeleccionado).getUsuario().equals(usuarioSeleccionado.getUsuario())) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "El nombre de usuario ingresado ya existe"));
            }
        } catch (Exception e) {
            // Log
        }
    }
    
    public String radioBtnRol(Usuario user) {
        String rol = "Estudiante";
        if (user.getRol().equals("A")) {
            rol = "Administrador";
        }
        return rol;
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

    public boolean globalFilterFunction1(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
        if (LangUtils.isValueBlank(filterText)) {
            return true;
        }

        int filterInt = getInteger1(filterText);

        Usuario user = (Usuario) value;
        return user.getUsuario().toLowerCase().contains(filterText)
                || user.getClave().toLowerCase().contains(filterText)
                || user.getRol().toLowerCase().contains(filterText)
                || user.getEstudiante_idestudiante().getIdestudiante() == filterInt;
    }

    private int getInteger1(String string) {
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException ex) {
            return 0;
        }
    }

}
