
package com.savage.model;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findByIdUsuario", query = "SELECT u FROM Usuario u WHERE u.estudiante_idestudiante = :estudiante_idestudiante"),
    @NamedQuery(name = "Usuario.findByUsuario", query = "SELECT u FROM Usuario u WHERE u.usuario = :usuario"),
})
@Table(name = "usuario")
public class Usuario implements Serializable{
    
    @Id
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="estudiante_idestudiante", nullable = false)
    private Estudiante estudiante_idestudiante;
    
    @Column(name = "usuario")
    private String usuario;
    
    @Column(name = "contrasena")
    private String clave;
    
    @Column(name = "Rol")
    private String rol = "E"; // por defento (cambiar en caso de errores)

    public Estudiante getEstudiante_idestudiante() {
        return estudiante_idestudiante;
    }

    public void setEstudiante_idestudiante(Estudiante estudiante_idestudiante) {
        this.estudiante_idestudiante = estudiante_idestudiante;
    }
    
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getRol() {
        return rol;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Usuario other = (Usuario) obj;
        if (this.estudiante_idestudiante != other.estudiante_idestudiante) {
            return false;
        }
        return true;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "Usuario{" + "codigo=" + estudiante_idestudiante + '}';
    } 
}
