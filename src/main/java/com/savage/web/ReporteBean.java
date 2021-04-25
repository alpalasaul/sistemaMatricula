package com.savage.web;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@Named
@RequestScoped
public class ReporteBean implements Serializable {

    private StreamedContent fileCurso;
    private StreamedContent fileEstudianteM;
    private StreamedContent fileEstudianteR;
    private StreamedContent fileProfesor;

    @PostConstruct
    public void init() {
        reporteCurso();
        reporteEstudianteM();
        reporteEstudianteR();
        reporteProfesor();
    }

    public StreamedContent reporteCurso() {
        fileCurso = DefaultStreamedContent.builder()
                .name("ReporteCurso.pdf")
                .contentType("image/png")
                .stream(() -> FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/resources/documentos/ReporteCurso.pdf"))
                .build();
        return fileCurso;
    }

    public StreamedContent reporteEstudianteM() {
        fileEstudianteM = DefaultStreamedContent.builder()
                .name("EstudiantesMatriculados.pdf")
                .contentType("image/png")
                .stream(() -> FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/resources/documentos/EstudiantesMatriculados.pdf"))
                .build();
        return fileEstudianteM;
    }

    public StreamedContent reporteEstudianteR() {
        fileEstudianteR = DefaultStreamedContent.builder()
                .name("EstudiantesRegistrados.pdf")
                .contentType("image/png")
                .stream(() -> FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/resources/documentos/EstudiantesRegistrados.pdf"))
                .build();
        return fileEstudianteR;
    }

    public StreamedContent reporteProfesor() {
        fileProfesor = DefaultStreamedContent.builder()
                .name("CursoEstudiante.pdf")
                .contentType("image/png")
                .stream(() -> FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/resources/documentos/CursoEstudiante.pdf"))
                .build();
        return fileProfesor;
    }

    public StreamedContent getFileCurso() {
        return fileCurso;
    }

    public void setFileCurso(StreamedContent fileCurso) {
        this.fileCurso = fileCurso;
    }

    public StreamedContent getFileEstudianteM() {
        return fileEstudianteM;
    }

    public void setFileEstudianteM(StreamedContent fileEstudianteM) {
        this.fileEstudianteM = fileEstudianteM;
    }

    public StreamedContent getFileEstudianteR() {
        return fileEstudianteR;
    }

    public void setFileEstudianteR(StreamedContent fileEstudianteR) {
        this.fileEstudianteR = fileEstudianteR;
    }

    public StreamedContent getFileProfesor() {
        return fileProfesor;
    }

    public void setFileProfesor(StreamedContent fileProfesor) {
        this.fileProfesor = fileProfesor;
    }
}
