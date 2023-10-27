package com.backend.parcial.dao.impl;

import com.backend.parcial.dao.IDao;
import com.backend.parcial.model.Odontologo;
import org.apache.log4j.Logger;

import java.util.List;


public class OdontologoDaoEnMemoria implements IDao<Odontologo> {

    private final Logger LOGGER = Logger.getLogger(OdontologoDaoEnMemoria.class);
    private List<Odontologo> odontologoMemoria;

    public OdontologoDaoEnMemoria(List<Odontologo> odontologoMemoria) {
        this.odontologoMemoria = odontologoMemoria;
    }


    @Override
    public Odontologo registrar(Odontologo odontologo) {
        Odontologo odontologoGuardado = null;
        int id = odontologoMemoria.size() +1;
        odontologoMemoria.add(odontologo);
        odontologoGuardado = new Odontologo(id, odontologo.getNumeroMatricula(), odontologo.getNombre(),odontologo.getApellido());
        LOGGER.info("Odontologo guardado: " + odontologoGuardado);

        return odontologoGuardado;
    }

    @Override
    public List<Odontologo> listarTodos() {
        LOGGER.info("Listado de Odontologos: " + odontologoMemoria);
        return odontologoMemoria;
    }
}
