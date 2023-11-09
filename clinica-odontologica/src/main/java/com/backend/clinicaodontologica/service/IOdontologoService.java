package com.backend.clinicaodontologica.service;

import com.backend.clinicaodontologica.model.Odontologo;
import com.backend.clinicaodontologica.model.Paciente;

import java.util.List;

public interface IOdontologoService {
   Odontologo registrarOdontologo(Odontologo odontologo);
    List<Odontologo> listarOdontologos();
    Odontologo buscarOdontologoPorId(int id);
}
