package com.backend.clinicaodontologica.service.impl;

import com.backend.clinicaodontologica.dto.entrada.paciente.DomicilioEntradaDto;
import com.backend.clinicaodontologica.dto.entrada.paciente.PacienteEntradaDto;
import com.backend.clinicaodontologica.dto.salida.paciente.PacienteSalidaDto;
import com.backend.clinicaodontologica.exceptions.ResourceNotFoundException;

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
    @Autowired
    private PacienteService pacienteService;

    @Test
     @Order(1)
    void deberiaRegistrarPacienteJuanYRetornarId(){
        PacienteEntradaDto pacienteEntradaDto = new PacienteEntradaDto("Juan", "Perez",555999, LocalDate.of(2023,01,01),
                new DomicilioEntradaDto("calle",12345,"Santiago","Santiago"));

        PacienteSalidaDto pacienteSalidaDto =
        pacienteService.registrarPaciente(pacienteEntradaDto);

        assertNotNull(pacienteSalidaDto.getId());
        assertEquals("Juan",pacienteSalidaDto.getNombre());
    }
    @Test
    @Order(2)
    void deberiaEliminarElPacienteConId1oLanzarResourceNotFound(){
        try{
            pacienteService.eliminarPaciente(1L);
        } catch (Exception exception){
            exception.printStackTrace();
        };
        assertThrows(ResourceNotFoundException.class, () -> pacienteService.eliminarPaciente(1L));
    }
    @Test
    @Order(3)
    void zdeberiaRetornarunaListaVacia(){
        List<PacienteSalidaDto> pacienteSalidaDtoList= pacienteService.listarPacientes();
        assertTrue(pacienteSalidaDtoList.isEmpty());
    }

}