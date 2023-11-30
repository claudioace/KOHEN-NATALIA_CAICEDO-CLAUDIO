package com.backend.clinicaodontologica.service.impl;

import com.backend.clinicaodontologica.dto.entrada.odontologo.OdontologoEntradaDto;
import com.backend.clinicaodontologica.dto.modificacion.OdontologoModificacionEntradaDto;
import com.backend.clinicaodontologica.dto.salida.odontologo.OdontologoSalidaDto;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OdontologoServiceTest {
    @Autowired
    private OdontologoService odontologoService;

    @Test
    @Order(1)
    void deberiaCrearOdontologoConMatricula2020() {
        OdontologoEntradaDto odontologoEntradaDto = new OdontologoEntradaDto(2020, "Jhon", "Perezeition");
        OdontologoSalidaDto odontologoSalidaDto = odontologoService.registrarOdontologo(odontologoEntradaDto);
        assertEquals(2020, odontologoSalidaDto.getNumeroMatricula());
    }

    ;

    @Test
    @Order(2)
    void deberiaEncontrarOdontologoConId1() {
        OdontologoSalidaDto odontologoSalidaDto = odontologoService.buscarOdontologoPorId(1L);
        assertNotNull(odontologoSalidaDto.getId());
    }

    @Test
    @Order(3)
    void deberiaActualizarElNombredelOdontologoId1AErnesto() {
        OdontologoModificacionEntradaDto odontologoAModificar = new OdontologoModificacionEntradaDto(1L, 2020, "Ernesto", "Perezeition");
        OdontologoSalidaDto odontologoSalidaDto = null;
        try {
            odontologoSalidaDto = odontologoService.actualizarOdontologo(odontologoAModificar);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        ;
        assertEquals("Ernesto", odontologoSalidaDto.getNombre());
        assertNotEquals("Jhon", odontologoSalidaDto.getNombre());
    }
}