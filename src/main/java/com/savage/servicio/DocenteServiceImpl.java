
package com.savage.servicio;

import com.savage.datos.DocenteDao;
import com.savage.model.Docente;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class DocenteServiceImpl  implements DocenteService{
    
    @Inject
    private DocenteDao docenteDao;
        
    @Override
    public List<Docente> listarDocente() {
        return docenteDao.findAllDocente();
    }
    
    @Override
    public List<Docente> listarDocenteEstado() {
        return docenteDao.findAllDocenteEstado();
    }

    @Override
    public Docente encontrarDocentePorIdDocente(Docente docente) {
        return docenteDao.findDocenteByIdDocente(docente);
    }

    @Override
    public Docente encontrarDocentePorCedula(Docente docente) {
        return docenteDao.findDocenteByCedula(docente);
    }

    @Override
    public void registrarDocente(Docente docente) {
        docenteDao.insertDocente(docente);
    }

    @Override
    public void modificarDocente(Docente docente) {
        
            docenteDao.updateDocente(docente);
       
    }

    @Override
    public void eliminarDocente(Docente docente) {
        docenteDao.deleteDocente(docente);
    }
    
}
