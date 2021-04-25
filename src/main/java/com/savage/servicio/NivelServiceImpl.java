package com.savage.servicio;

import com.savage.datos.NivelDao;
import com.savage.model.Nivel;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class NivelServiceImpl implements NivelService {

    @Inject
    private NivelDao nivelDao;

    @Override
    public List<Nivel> listarNivel() {
        return nivelDao.findAllNivel();
    }

    @Override
    public List<Nivel> listarNivelEstado() {
        return nivelDao.findAllNivelEstado();
    }

    @Override
    public Nivel encontrarNivelPorIdnivel(Nivel nivel) {
        return nivelDao.findNivelByIdnivel(nivel);
    }

    @Override
    public void registrarNivel(Nivel nivel) {
        nivelDao.insertNivel(nivel);
    }

    @Override
    public void modificarNivel(Nivel nivel) {
        nivelDao.updateNivel(nivel);
    }

    @Override
    public void eliminarNivel(Nivel nivel) {
        nivelDao.deleteNivel(nivel);
    }

}
