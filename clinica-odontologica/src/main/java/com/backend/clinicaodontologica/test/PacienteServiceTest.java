package com.backend.clinicaodontologica.test;


import com.backend.clinicaodontologica.service.impl.PacienteService;


class PacienteServiceTest {
    private PacienteService pacienteService;

 /*   @BeforeAll
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
    public void deberiaRetornarListaNoVaciaPacienteH2() {
        pacienteService = new PacienteService(new PacienteDaoH2());
        assertFalse(pacienteService.listarPacientes().isEmpty());
    }*/

}