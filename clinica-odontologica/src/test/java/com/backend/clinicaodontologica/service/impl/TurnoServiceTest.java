package com.backend.clinicaodontologica.service.impl;
import com.backend.clinicaodontologica.dto.entrada.odontologo.OdontologoEntradaDto;
import com.backend.clinicaodontologica.dto.entrada.paciente.DomicilioEntradaDto;
import com.backend.clinicaodontologica.dto.entrada.paciente.PacienteEntradaDto;
import com.backend.clinicaodontologica.dto.entrada.turno.TurnoEntradaDto;
import com.backend.clinicaodontologica.dto.salida.odontologo.OdontologoSalidaDto;
import com.backend.clinicaodontologica.dto.salida.paciente.PacienteSalidaDto;
import com.backend.clinicaodontologica.dto.salida.turno.TurnoSalidaDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
 class TurnoServiceTest {

    @Autowired
    private static TurnoService turnoService;
    @Autowired
    private static OdontologoService odontologoService;
    @Autowired
    private static PacienteService pacienteService;


/*
    @BeforeAll
    static void setup() {

         paciente1 = pacienteService.registrarPaciente(new PacienteEntradaDto("Claudio", "Caicedo", 123, LocalDate.of(2024, 01, 01),
                new DomicilioEntradaDto("calle", 12345, "Santiago", "Santiago")));

        odontologo1 = odontologoService.registrarOdontologo(new OdontologoEntradaDto(2020, "Jhon", "Perezeition"));

    }*/


    @Test
    public void crearTurno() throws Exception {

        PacienteSalidaDto paciente1 = pacienteService.registrarPaciente(new PacienteEntradaDto("Claudio", "Caicedo", 123, LocalDate.of(2024, 01, 01),
                new DomicilioEntradaDto("calle", 12345, "Santiago", "Santiago")));

        OdontologoSalidaDto odontologo1 = odontologoService.registrarOdontologo(new OdontologoEntradaDto(2020, "Jhon", "Perezeition"));


    }
        // Crear el turno
        @Test
        public void deberiaRegistrarUnTurnoConOdontologoId1(){
        TurnoSalidaDto turno = turnoService.registrarTurno(new TurnoEntradaDto(LocalDateTime.of(2024,1,1,11,1,1),odontologo1,paciente1));

            assertEquals(1,turno.getOdontologoSalidaDto().getId());
            }
}

}
