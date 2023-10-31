package com.backend.parcial.service;

import com.backend.parcial.dao.IDao;
import com.backend.parcial.model.Paciente;

import java.util.List;

public class PacienteService {
    private IDao<Paciente> pacienteIDao;

    public PacienteService(IDao<Paciente> pacienteIDao) {
        this.pacienteIDao = pacienteIDao;
    }
    public Paciente registrarPaciente(Paciente paciente){
        return pacienteIDao.registrar(paciente);
    };
    public List<Paciente> listarPacientes() {
        return pacienteIDao.listarTodos();
    }
}
