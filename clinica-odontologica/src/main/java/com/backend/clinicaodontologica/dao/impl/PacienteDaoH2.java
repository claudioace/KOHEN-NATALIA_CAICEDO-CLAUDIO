package com.backend.clinicaodontologica.dao.impl;

import com.backend.clinicaodontologica.dao.H2Connection;
import com.backend.clinicaodontologica.dao.IDao;
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


    @Override
    public Paciente registrar(Paciente paciente) {

        Paciente pacienteRegistrado = null;
        Connection connection = null;

        try {
            connection = H2Connection.getConnection();
            connection.setAutoCommit(false);

            String INSERT = "INSERT INTO PACIENTES (NOMBRE, APELLIDO, DOMICILIO, DNI, FECHAINGRESO) VALUES (?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, paciente.getNombre());
            preparedStatement.setString(2, paciente.getApellido());
            preparedStatement.setString(3, paciente.getDomicilio());
            preparedStatement.setInt(4, paciente.getDni());
            preparedStatement.setDate(5, Date.valueOf(paciente.getFechaIngreso()));
            preparedStatement.execute();

            pacienteRegistrado = new Paciente(paciente.getNombre(), paciente.getApellido(), paciente.getDomicilio(), paciente.getDni(), paciente.getFechaIngreso());

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

        try{
            connection = H2Connection.getConnection();
            String SELECT = "SELECT * FROM PACIENTES WHERE ID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                paciente = crearObjetoPaciente(resultSet);
            }

            if(paciente == null) LOGGER.error("No se ha encontrado el paciente con id: " + id);
            else LOGGER.info("Se ha encontrado el paciente: " + paciente);



        } catch (Exception e){
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception ex){
                LOGGER.error("Ha ocurrido un error al intentar cerrar la bdd. " + ex.getMessage());
                ex.printStackTrace();
            }
        }


        return paciente;
    }

    private Paciente crearObjetoPaciente(ResultSet resultSet) throws SQLException {
        return new Paciente(resultSet.getInt("id"), resultSet.getString("nombre"), resultSet.getString("apellido"), resultSet.getString("domicilio"), resultSet.getInt("dni"), resultSet.getDate("fechaingreso").toLocalDate());
    }
}
