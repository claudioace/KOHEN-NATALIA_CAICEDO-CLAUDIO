package com.backend.clinicaodontologica.service.impl;

import com.backend.clinicaodontologica.dto.entrada.paciente.DomicilioEntradaDto;
import com.backend.clinicaodontologica.dto.entrada.paciente.PacienteEntradaDto;
import com.backend.clinicaodontologica.dto.salida.paciente.PacienteSalidaDto;
import com.backend.clinicaodontologica.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PacienteServiceTest {
    PacienteEntradaDto paciente1;
    PacienteSalidaDto pacienteSalida;
    @Autowired
    private PacienteService pacienteService;

    @Test
    @Order(1)
    void deberiaCrearPacienteJuanYRetornarId() {
        PacienteEntradaDto pacienteEntradaDto = new PacienteEntradaDto("Juan", "Perez", 555999, LocalDate.of(2023, 01, 01),
                new DomicilioEntradaDto("calle", 12345, "Santiago", "Santiago"));

        PacienteSalidaDto pacienteSalidaDto =
                pacienteService.registrarPaciente(pacienteEntradaDto);

        assertNotNull(pacienteSalidaDto.getId());
        assertEquals("Juan", pacienteSalidaDto.getNombre());
    }

    @Test
    @Order(2)
    void deberiaEliminarElPacienteConId1oLanzarResourceNotFound() {
        try {
            pacienteService.eliminarPaciente(1L);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        ;
        assertThrows(ResourceNotFoundException.class, () -> pacienteService.eliminarPaciente(1L));
    }

    @BeforeEach
    void setUp() {
        //crea un unico paciente
        paciente1 = new PacienteEntradaDto("Claudio", "Caicedo", 123, LocalDate.of(2024, 01, 01),
                new DomicilioEntradaDto("calle", 12345, "Santiago", "Santiago"));
        pacienteSalida = pacienteService.registrarPaciente(paciente1);

    }

    @Test
    @Order(3)
    void deberiaRetornarunaListaNoVacia() {
        List<PacienteSalidaDto> pacienteSalidaDtoList = pacienteService.listarPacientes();
        assertFalse(pacienteSalidaDtoList.isEmpty());
    }

}