package com.backend.clinicaodontologica.service.impl;

import com.backend.clinicaodontologica.dto.entrada.odontologo.OdontologoEntradaDto;
import com.backend.clinicaodontologica.dto.modificacion.OdontologoModificacionEntradaDto;
import com.backend.clinicaodontologica.dto.salida.odontologo.OdontologoSalidaDto;
import com.backend.clinicaodontologica.entity.Odontologo;
import com.backend.clinicaodontologica.exceptions.ResourceNotFoundException;
import com.backend.clinicaodontologica.repository.OdontologoRepository;
import com.backend.clinicaodontologica.service.IOdontologoService;
import com.backend.clinicaodontologica.utils.JsonPrinter;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OdontologoService implements IOdontologoService {
    private final Logger LOGGER = LoggerFactory.getLogger(OdontologoService.class);
    private final OdontologoRepository odontologoRepository;
    private final ModelMapper modelMapper;

    public OdontologoService(OdontologoRepository odontologoRepository, ModelMapper modelMapper) {
        this.odontologoRepository = odontologoRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public OdontologoSalidaDto registrarOdontologo(OdontologoEntradaDto odontologo) {


        LOGGER.info("OdontologoEntradaDto: " + JsonPrinter.toString(odontologo));

        Odontologo odontologoEntidad = modelMapper.map(odontologo, Odontologo.class);


        Odontologo odontologoAPersistir = odontologoRepository.save(odontologoEntidad);

        OdontologoSalidaDto odontologoSalidaDto = modelMapper.map(odontologoAPersistir, OdontologoSalidaDto.class);
        LOGGER.info("OdontologoSalidaDto: " + JsonPrinter.toString(odontologoSalidaDto));
        return odontologoSalidaDto;
    }

    @Override
    public List<OdontologoSalidaDto> listarOdontologos() {
        List<OdontologoSalidaDto> odontologosSalidaDto = odontologoRepository.findAll().stream()
                .map(odontologo -> modelMapper.map(odontologo, OdontologoSalidaDto.class)).toList();
        LOGGER.info("Listado de todos los odontologo: {}", JsonPrinter.toString(odontologosSalidaDto));
        return odontologosSalidaDto;
    }

    @Override
    public OdontologoSalidaDto buscarOdontologoPorId(Long id) {
        Odontologo odontologoBuscado = odontologoRepository.findById(id).orElse(null);
        OdontologoSalidaDto odontologoEncontrado = null;

        if (odontologoBuscado != null) {
            odontologoEncontrado = modelMapper.map(odontologoBuscado, OdontologoSalidaDto.class);
            LOGGER.info("Odontologo encontrado: {}", JsonPrinter.toString(odontologoEncontrado));
        } else LOGGER.error("El id no se encuentra registrado en la base de datos");

        return odontologoEncontrado;
    }


    @Override
    public OdontologoSalidaDto actualizarOdontologo(OdontologoModificacionEntradaDto odontologo) throws ResourceNotFoundException {

        LOGGER.info("OdontologoEntradaDto: " + JsonPrinter.toString(odontologo));
        Odontologo odontologoEntidad = modelMapper.map(odontologo, Odontologo.class);


        Odontologo odontologoAModificar = odontologoRepository.findById(odontologoEntidad.getId()).orElse(null);
        OdontologoSalidaDto odontologoSalidaDto = null;

        if (odontologoAModificar != null) {
            odontologoAModificar = odontologoEntidad;
            odontologoRepository.save(odontologoAModificar);


            odontologoSalidaDto = modelMapper.map(odontologoAModificar, OdontologoSalidaDto.class);
            LOGGER.info("Odontologo modificado: {}", JsonPrinter.toString(odontologoSalidaDto));
        } else {
            LOGGER.error("No fue posible actualizar el odontólogo porque no se encuentra en nuestra base de datos");
            throw new ResourceNotFoundException("No fue posible actualizar los datos. Odontologo no se encuentra registrado");
        }
        return odontologoSalidaDto;
    }

    @Override
    public void eliminarOdontologo(Long id) throws ResourceNotFoundException {
        if (odontologoRepository.findById(id).orElse(null) != null) {
            odontologoRepository.deleteById(id);
            LOGGER.warn("Se ha eliminado el odontólogo con id: {}", id);
        } else {
            LOGGER.error("No se ha encontrado el odontólogo con id {}", id);
            throw new ResourceNotFoundException("No se ha encontrado el odontologo con id " + id);
        }
    }


}
