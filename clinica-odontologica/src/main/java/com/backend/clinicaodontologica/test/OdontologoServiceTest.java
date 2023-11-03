package com.backend.clinicaodontologica.test;

//import com.backend.clinicaodontologica.dao.impl.OdontologoDaoEnMemoria;

import com.backend.clinicaodontologica.dao.impl.OdontologoDaoH2;
import com.backend.clinicaodontologica.service.OdontologoService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

import java.sql.Connection;
import java.sql.DriverManager;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class OdontologoServiceTest {
    private OdontologoService odontologoService;

    @BeforeAll
    static void doBefore() {
        Connection connection = null;
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:~/clinica;INIT=RUNSCRIPT FROM 'create.sql'", "sa", "sa");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    @Test
    public void deberiaRetornarListaNoVaciaOdontologoH2() {
        odontologoService = new OdontologoService(new OdontologoDaoH2());
        assertFalse(odontologoService.listarOdontologos().isEmpty());

    }

   /*
   @Test
    public void deberiaRetornarListaNoVaciaEnMemoria() {
        odontologoService = new OdontologoService(new OdontologoDaoEnMemoria(new ArrayList<>()));
        Odontologo odontologoAPersistir = new Odontologo(333, "Juan", "Perez");
        Odontologo odontologo = odontologoService.registrarOdontologo(odontologoAPersistir);
        assertNotNull(odontologo.getId()); //Si fue registrado exitosamente, deberia tener un id
    }
    */


}