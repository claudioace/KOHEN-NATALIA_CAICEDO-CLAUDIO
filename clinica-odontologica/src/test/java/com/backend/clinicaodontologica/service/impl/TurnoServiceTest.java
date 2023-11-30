package com.backend.clinicaodontologica.service.impl;

import com.backend.clinicaodontologica.dto.entrada.odontologo.OdontologoEntradaDto;
import com.backend.clinicaodontologica.dto.entrada.paciente.DomicilioEntradaDto;
import com.backend.clinicaodontologica.dto.entrada.paciente.PacienteEntradaDto;
import com.backend.clinicaodontologica.dto.entrada.turno.TurnoEntradaDummy;
import com.backend.clinicaodontologica.dto.modificacion.TurnoModificacionEntradaDummy;
import com.backend.clinicaodontologica.dto.salida.odontologo.OdontologoSalidaDto;
import com.backend.clinicaodontologica.dto.salida.paciente.PacienteSalidaDto;
import com.backend.clinicaodontologica.dto.salida.turno.TurnoSalidaDto;
import com.backend.clinicaodontologica.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Execution(ExecutionMode.SAME_THREAD)
class TurnoServiceTest {

    PacienteEntradaDto paciente1;
    PacienteSalidaDto pacienteSalida;
    OdontologoEntradaDto odontologo1;
    OdontologoEntradaDto odontologo2;
    OdontologoSalidaDto odontologoSalida;
    @Autowired
    private TurnoService turnoService;
    @Autowired
    private OdontologoService odontologoService;
    @Autowired
    private PacienteService pacienteService;


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

    @Test
    @Order(1)
    void deberiaCrearUnTurnoConOdontologoId1() {
        TurnoEntradaDummy turnoDummy = new TurnoEntradaDummy(LocalDateTime.of(2024, 1, 1, 11, 1, 1), 1L, 1L);
        TurnoSalidaDto turno = turnoService.registrarTurno(turnoDummy);

        assertEquals(1, turno.getOdontologoSalidaDto().getId());
    }

    @Test
    @Order(2)
    void deberiaModificarTurnoId1ConOdontologoId2() {
        TurnoModificacionEntradaDummy turnoDummy = new TurnoModificacionEntradaDummy(
                1L,
                LocalDateTime.of(2024, 1, 1, 11, 1, 1),
                2L,
                1L);
        TurnoSalidaDto turnoSalidaDto = null;
        try {
            turnoSalidaDto = turnoService.actualizarTurno(turnoDummy);
        } catch (ResourceNotFoundException exception) {
            exception.printStackTrace();
        }
        ;
        assertEquals(2, turnoSalidaDto.getOdontologoSalidaDto().getId());
    }

    @Test
    @Order(3)
    void deberiaEncontrarUnTurnoConId1() {
        TurnoSalidaDto turnoEncontrado = turnoService.buscarTurnoPorId(1L);
        assertNotNull(turnoEncontrado.getId(), "El turno es nulo");
        assertEquals(1, turnoEncontrado.getId(), "El ID del turno encontrado no coincide con el ID esperado");
    }

    @Test
    @Order(4)
    void deberiaEliminarTurnoConId1oLanzarExcepcionResourceNotFound() {
        try {
            turnoService.eliminarTurno(1L);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        ;
        assertThrows(ResourceNotFoundException.class, () -> turnoService.eliminarTurno(1L));
    }

    @Test
    @Order(5)
    void deberiaRetornarunaListaConTurnos() {
        List<TurnoSalidaDto> listaTurnos = turnoService.listarTurnos();
        assertFalse(listaTurnos.isEmpty());
    }
}



