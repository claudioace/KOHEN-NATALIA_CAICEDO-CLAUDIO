package com.backend.clinicaodontologica.service;

import com.backend.clinicaodontologica.dao.IDao;
import com.backend.clinicaodontologica.dto.entrada.paciente.PacienteEntradaDto;
import com.backend.clinicaodontologica.dto.salida.PacienteSalidaDto;
import com.backend.clinicaodontologica.model.Paciente;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteService implements IPacienteService{
    private IDao<Paciente> pacienteIDao;
    private final Logger LOGGER = LoggerFactory.getLogger(PacienteService.class);
    private ModelMapper modelMapper;

    public PacienteService(IDao<Paciente> pacienteIDao, ModelMapper modelMapper) {
        this.pacienteIDao = pacienteIDao;
        this.modelMapper = modelMapper;
        configureMapping();
    }

    @Override
    public PacienteSalidaDto registrarPaciente(PacienteEntradaDto paciente) {

    //convertimos mediante el mapper de dto a entidad
        LOGGER.info("PacienteEntradaDto")
    Paciente pacienteEntidad = modelMapper.map(paciente, Paciente.class);
    //lamamos a la capa de persistencia
        return pacienteIDao.registrar(pacienteEntidad);
    }

    public List<PacienteSalidaDto> listarPacientes() {
        List<PacienteSalidaDto> pacientesSalidaDto = pacienteIDao.listarTodos().stream()
                .map(paciente -> modelMapper.map(paciente,PacienteSalidaDto.class)).toList();
        LOGGER.info("listado de todos los pacientes: {} "+ pacientesSalidaDto);
        return pacientesSalidaDto;
    }

    @Override
    public Paciente buscarPacientePorId(int id) {
        return pacienteIDao.buscarPorId(id);
    }

    @Override
    public Paciente actualizarPaciente(Paciente paciente) {
        return pacienteIDao.actualizar(paciente);
    }
    private void configureMapping(){
        modelMapper.typeMap(PacienteEntradaDto.class, Paciente.class)
                .addMappings(modelMapper -> modelMapper.map(PacienteEntradaDto::getDomicilioEntradaDto, Paciente::setDomicilio));
        modelMapper.typeMap( Paciente.class, PacienteSalidaDto.class)
                .addMappings(modelMapper -> modelMapper.map(Paciente::getDomicilioEntradaDto, Paciente::setDomicilio));


    }
}
