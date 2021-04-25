
package com.savage.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Entity
@NamedQueries({
    @NamedQuery(name = "Matricula.findAll", query = "SELECT m FROM Matricula m"),
    @NamedQuery(name = "Matricula.findByIdmatricula", query = "SELECT m FROM Matricula m WHERE m.idmatricula = :idmatricula"),
})
@Table(name = "matricula")
public class Matricula implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idmatricula;
        
    @ManyToOne
    @JoinColumn(name = "estudiante_idestudiante", nullable = false)
    private Estudiante estudiante;
    
    @ManyToOne
    @JoinColumn(name = "curso_idcurso", nullable = false)
    private Curso curso;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_matricula", insertable = false)
    private Date fechaMatricula;
    
    @Column(name = "estado")
    private int estado = 1;
    
    @Column(name = "estado_cobro")
    private int estadoCobro = 0;

    public int getIdmatricula() {
        return idmatricula;
    }

    public void setIdmatricula(int idmatricula) {
        this.idmatricula = idmatricula;
    }

    public Date getFechaMatricula() {
        return fechaMatricula;
    }

    public void setFechaMatricula(Date fechaMatricula) {
        this.fechaMatricula = fechaMatricula;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getEstadoCobro() {
        return estadoCobro;
    }

    public void setEstadoCobro(int estadoCobro) {
        this.estadoCobro = estadoCobro;
    }
    
    
    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
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
        final Matricula other = (Matricula) obj;
        if (this.idmatricula != other.idmatricula) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Matricula{" + "codigo=" + idmatricula + '}';
    }
}
