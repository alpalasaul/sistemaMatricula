
package com.savage.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Entity
@NamedQueries({
    @NamedQuery(name = "Curso.findAll", query = "SELECT c FROM Curso c"),
    @NamedQuery(name = "Curso.findAllEstado", query = "SELECT c FROM Curso c WHERE c.estado = 1"),
    @NamedQuery(name = "Curso.findByIdcurso", query = "SELECT c FROM Curso c WHERE c.idcurso = :idcurso"),
})
@Table(name="curso")
public class Curso implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idcurso;
    
    @ManyToOne
    @JoinColumn(name = "nivel_idnivel", nullable = false)
    private Nivel nivel;
    
    @ManyToOne
    @JoinColumn(name = "docente_idDocente", nullable = false)
    private Docente docente;
    
    @Column(name = "nombre")
    private String nombre;
    
    @Column(name = "cupo")
    private int cupo;
    
    @Column(name = "url")
    private String url = "images/js.png";
    
    @Column(name = "descripcion")
    private String descripcion;
    
    
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_inicio")
    private Date fechaInicio;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_finalizacion")
    private Date fechaFinalizacion;
    
    @Column(name = "estado")
    private int estado = 1;

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
    
    public int getIdcurso() {
        return idcurso;
    }

    public void setIdcurso(int idcurso) {
        this.idcurso = idcurso;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Nivel getNivel_idnivel() {
        return nivel;
    }

    public void setNivel_idnivel(Nivel nivel_idnivel) {
        this.nivel = nivel_idnivel;
    }

    public Docente getDocente_iddocente() {
        return docente;
    }

    public void setDocente_iddocente(Docente docente_iddocente) {
        this.docente = docente_iddocente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCupo() {
        return cupo;
    }

    public void setCupo(int cupo) {
        this.cupo = cupo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFinalizacion() {
        return fechaFinalizacion;
    }

    public void setFechaFinalizacion(Date fechaFinalizacion) {
        this.fechaFinalizacion = fechaFinalizacion;
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
        final Curso other = (Curso) obj;
        if (this.idcurso != other.idcurso) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Curso{" + "codigo=" + idcurso + '}';
    }
}
