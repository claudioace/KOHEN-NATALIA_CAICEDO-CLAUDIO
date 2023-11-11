package com.backend.clinicaodontologica.dao.impl;

import com.backend.clinicaodontologica.dao.H2Connection;
import com.backend.clinicaodontologica.dao.IDao;
import com.backend.clinicaodontologica.model.Domicilio;
import com.backend.clinicaodontologica.model.Paciente;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PacienteDaoH2 implements IDao<Paciente> {
    private final Logger LOGGER = LoggerFactory.getLogger(PacienteDaoH2.class);
    private DomicilioDaoH2 domicilioDaoH2;

    @Override
    public Paciente registrar(Paciente paciente) {


        Paciente pacienteRegistrado = null;
        Connection connection = null;


        try {
            connection = H2Connection.getConnection();
            connection.setAutoCommit(false);

            String INSERT = "INSERT INTO PACIENTES (NOMBRE, APELLIDO, DOMICILIO, DNI, FECHAINGRESO) VALUES (?, ?, ?, ?, ?)";

            domicilioDaoH2 = new DomicilioDaoH2();
            Domicilio domicilioRegistrado = domicilioDaoH2.registrar(paciente.getDomicilio());

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO PACIENTES (NOMBRE, APELLIDO, DNI, FECHA, DOMICILIO_ID) VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, paciente.getNombre());
            preparedStatement.setString(2, paciente.getApellido());
            preparedStatement.setInt(3, paciente.getDni());
            preparedStatement.setDate(4, Date.valueOf(paciente.getFechaIngreso()));
            preparedStatement.setInt(5, domicilioRegistrado.getId());
            preparedStatement.execute();


            pacienteRegistrado = new Paciente(paciente.getNombre(), paciente.getApellido(), paciente.getDni(), paciente.getFechaIngreso(), domicilioRegistrado);

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                pacienteRegistrado.setId(resultSet.getInt("id"));
            }

            LOGGER.info("Se ha registrado el paciente " + pacienteRegistrado);
            connection.commit();

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
            if (connection != null) {
                try {
                    connection.rollback();
                    LOGGER.info("Tuvimos un problema");
                    LOGGER.error(e.getMessage());
                    e.printStackTrace();
                } catch (SQLException exception) {
                    LOGGER.error(exception.getMessage());
                    exception.printStackTrace();
                }
            }
        } finally {
            try {
                connection.close();
            } catch (Exception ex) {
                LOGGER.error("No se pudo cerrar la conexion: " + ex.getMessage());
            }
        }


        return pacienteRegistrado;
    }

    @Override
    public List<Paciente> listarTodos() {
        List<Paciente> pacientes = new ArrayList<>();
        Connection connection = null;
        try {
            connection = H2Connection.getConnection();
            String SELECT = "SELECT * FROM PACIENTES";
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Paciente pacienteCreado = crearObjetoPaciente(resultSet);
                pacientes.add(pacienteCreado);
            }

            LOGGER.info("Listado de Pacientes: " + pacientes);

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();

        } finally {
            try {
                connection.close();
            } catch (Exception ex) {
                LOGGER.error("Error al intentar cerrar la Base de Datos. " + ex.getMessage());
                ex.printStackTrace();
            }
        }


        return pacientes;
    }

    @Override
    public Paciente buscarPorId(int id) {
        Connection connection = null;
        Paciente paciente = null;

        try {
            connection = H2Connection.getConnection();
            String SELECT = "SELECT * FROM PACIENTES WHERE ID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                paciente = crearObjetoPaciente(resultSet);
            }

            if (paciente == null) LOGGER.error("No se ha encontrado el paciente con id: " + id);
            else LOGGER.info("Se ha encontrado el paciente: " + paciente);


        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception ex) {
                LOGGER.error("Ha ocurrido un error al intentar cerrar la bdd. " + ex.getMessage());
                ex.printStackTrace();
            }
        }


        return paciente;
    }

    @Override
    public Paciente actualizar(Paciente pacienteModificado) {

        Connection connection = null;
        try {
            connection = H2Connection.getConnection();
            connection.setAutoCommit(false);

            PreparedStatement ps = connection.prepareStatement("UPDATE PACIENTES SET NOMBRE = ?, APELLIDO = ?, DNI = ?, FECHA = ?, DOMICILIO_ID = ? WHERE ID = ?");
            ps.setString(1, pacienteModificado.getNombre());
            ps.setString(2, pacienteModificado.getApellido());
            ps.setInt(3, pacienteModificado.getDni());
            ps.setDate(4, Date.valueOf(pacienteModificado.getFechaIngreso()));
            ps.setInt(5, pacienteModificado.getDomicilio().getId());
            ps.setInt(6, pacienteModificado.getId());
            ps.execute();

            connection.commit();
            LOGGER.warn("El paciente con id " + pacienteModificado.getId() + "ha sido modificado: " + pacienteModificado);

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception ex) {
                LOGGER.error("Ha ocurrido un error al intentar cerrar la bdd. " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return pacienteModificado;
    }


    private Paciente crearObjetoPaciente(ResultSet resultSet) throws SQLException {
        Domicilio domicilio = new DomicilioDaoH2().buscarPorId(resultSet.getInt("domicilio_id"));

        return new Paciente(resultSet.getInt("id"), resultSet.getString("nombre"), resultSet.getString("apellido"), resultSet.getInt("dni"), resultSet.getDate("fecha").toLocalDate(), domicilio);
    }

}
