package com.backend.clinicaodontologica.service.impl;
import com.backend.clinicaodontologica.dto.entrada.odontologo.OdontologoEntradaDto;
import com.backend.clinicaodontologica.dto.entrada.paciente.DomicilioEntradaDto;
import com.backend.clinicaodontologica.dto.entrada.paciente.PacienteEntradaDto;
import com.backend.clinicaodontologica.dto.entrada.turno.TurnoEntradaDto;
import com.backend.clinicaodontologica.dto.modificacion.TurnoModificacionEntradaDto;
import com.backend.clinicaodontologica.dto.salida.odontologo.OdontologoSalidaDto;
import com.backend.clinicaodontologica.dto.salida.paciente.PacienteSalidaDto;
import com.backend.clinicaodontologica.dto.salida.turno.TurnoSalidaDto;
import com.backend.clinicaodontologica.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
 class TurnoServiceTest {

    @Autowired
    private TurnoService turnoService;
    @Autowired
    private OdontologoService odontologoService;
    @Autowired
    private PacienteService pacienteService;
    PacienteEntradaDto  paciente1;
    PacienteSalidaDto pacienteSalida;
    OdontologoEntradaDto odontologo1;
    OdontologoSalidaDto odontologoSalida;


    @BeforeEach
    void setUp() {
        paciente1 = new PacienteEntradaDto("Claudio", "Caicedo", 123, LocalDate.of(2024, 01, 01),
                new DomicilioEntradaDto("calle", 12345, "Santiago", "Santiago"));
        pacienteSalida = pacienteService.registrarPaciente(paciente1);

        odontologo1 = new OdontologoEntradaDto(2020, "Jhon", "Perezeition");
        odontologoSalida = odontologoService.registrarOdontologo(odontologo1);
    }

    @Test// resulta si no se hace el otro primero
    void deberiaCrearUnTurnoConOdontologoId1(){
        TurnoEntradaDto turnoEntrada =new TurnoEntradaDto(LocalDateTime.of(2024,1,1,11,1,1),odontologoSalida,pacienteSalida);
        TurnoSalidaDto turno = turnoService.registrarTurno(turnoEntrada);

            assertEquals(1,turno.getOdontologoSalidaDto().getId());
            };
    @Test
    void deberiaEncontrarUnTurnoConId1()  {
        TurnoSalidaDto turnoEncontrado = turnoService.buscarTurnoPorId(1L);
        assertNotNull(turnoEncontrado, "El turno es nulo");
        assertEquals(1, turnoEncontrado.getId(), "El ID del turno encontrado no coincide con el ID esperado");





        }

}



