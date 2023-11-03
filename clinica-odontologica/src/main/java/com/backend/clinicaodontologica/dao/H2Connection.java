package com.backend.clinicaodontologica.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class H2Connection {
    private static final Logger LOGGER = LoggerFactory.getLogger(H2Connection.class);

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        LOGGER.info("Conectado");
        return DriverManager.getConnection("jdbc:h2:~/clinica", "sa", "sa");

    }


}
