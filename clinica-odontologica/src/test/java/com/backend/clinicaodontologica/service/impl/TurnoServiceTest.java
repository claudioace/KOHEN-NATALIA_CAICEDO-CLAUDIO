package com.backend.clinicaodontologica.service.impl;
import com.backend.clinicaodontologica.dto.entrada.odontologo.OdontologoEntradaDto;
import com.backend.clinicaodontologica.dto.entrada.paciente.DomicilioEntradaDto;
import com.backend.clinicaodontologica.dto.entrada.paciente.PacienteEntradaDto;
import com.backend.clinicaodontologica.dto.entrada.turno.TurnoEntradaDto;
import com.backend.clinicaodontologica.dto.entrada.turno.TurnoEntradaDummy;
import com.backend.clinicaodontologica.dto.modificacion.TurnoModificacionEntradaDto;
import com.backend.clinicaodontologica.dto.modificacion.TurnoModificacionEntradaDummy;
import com.backend.clinicaodontologica.dto.salida.odontologo.OdontologoSalidaDto;
import com.backend.clinicaodontologica.dto.salida.paciente.PacienteSalidaDto;
import com.backend.clinicaodontologica.dto.salida.turno.TurnoSalidaDto;
import com.backend.clinicaodontologica.exceptions.ResourceNotFoundException;
import org.junit.Ignore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
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
    OdontologoEntradaDto odontologo2;
    OdontologoSalidaDto odontologoSalida;

/*
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
*/
@BeforeEach
void setUp() {
    paciente1 = new PacienteEntradaDto("Testeo", "Caicedo", 123, LocalDate.of(2024, 01, 01),
            new DomicilioEntradaDto("calle", 12345, "Santiago", "Santiago"));
    pacienteSalida = pacienteService.registrarPaciente(paciente1);

    odontologo1 = new OdontologoEntradaDto(2020, "Jhon", "Perezeition");
    odontologoSalida = odontologoService.registrarOdontologo(odontologo1);

    odontologo2 = new OdontologoEntradaDto(3030, "Ana", "Lopez");
    odontologoSalida = odontologoService.registrarOdontologo(odontologo2);
}


    @Test// resulta si no se hace el otro primero

    void adeberiaCrearUnTurnoConOdontologoId1(){
        TurnoEntradaDummy turnoDummy =new TurnoEntradaDummy(LocalDateTime.of(2024,1,1,11,1,1),1L,1L);
        TurnoSalidaDto turno = turnoService.registrarTurno(turnoDummy);

        assertEquals(1,turno.getOdontologoSalidaDto().getId());
    };

    @Test
    void bdeberiaEncontrarUnTurnoConId1()  {
        TurnoSalidaDto turnoEncontrado = turnoService.buscarTurnoPorId(1L);
        assertNotNull(turnoEncontrado, "El turno es nulo");
        assertEquals(1, turnoEncontrado.getId(), "El ID del turno encontrado no coincide con el ID esperado");
        };

    @Test
    void cdeberiaActualizarTurnoId1ConOdontologoId2(){
        TurnoModificacionEntradaDummy turnoDummy = new TurnoModificacionEntradaDummy(
                1L,
                LocalDateTime.of(2024,1,1,11,1,1),
                2L,
                1L);
        TurnoSalidaDto turnoSalidaDto = null;
        try {
            turnoSalidaDto = turnoService.actualizarTurno(turnoDummy);
        } catch (ResourceNotFoundException exception) {
            exception.printStackTrace();
        };

        assertEquals(2,turnoSalidaDto.getOdontologoSalidaDto().getId());
    };


}



