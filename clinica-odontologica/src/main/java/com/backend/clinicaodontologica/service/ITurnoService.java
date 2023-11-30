package com.backend.clinicaodontologica.service;

import com.backend.clinicaodontologica.dto.entrada.turno.TurnoEntradaDto;
import com.backend.clinicaodontologica.dto.entrada.turno.TurnoEntradaDummy;
import com.backend.clinicaodontologica.dto.modificacion.TurnoModificacionEntradaDto;
import com.backend.clinicaodontologica.dto.modificacion.TurnoModificacionEntradaDummy;
import com.backend.clinicaodontologica.dto.salida.turno.TurnoSalidaDto;
import com.backend.clinicaodontologica.exceptions.ResourceNotFoundException;


import java.time.LocalDateTime;
import java.util.List;


public interface ITurnoService {
 // TurnoSalidaDto registrarTurno(TurnoEntradaDto turnoEntradaDto);
    TurnoSalidaDto registrarTurno(TurnoEntradaDummy turnoEntradaDto);
    List<TurnoSalidaDto> listarTurnos();

    TurnoSalidaDto buscarTurnoPorId(Long id);

    TurnoSalidaDto actualizarTurno(TurnoModificacionEntradaDummy turno) throws ResourceNotFoundException;
    void eliminarTurno(Long id)throws ResourceNotFoundException;

}
