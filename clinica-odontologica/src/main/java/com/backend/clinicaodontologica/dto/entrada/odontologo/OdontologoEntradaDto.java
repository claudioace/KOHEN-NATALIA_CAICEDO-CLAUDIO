package com.backend.clinicaodontologica.dto.entrada.odontologo;

import javax.validation.constraints.*;

public class OdontologoEntradaDto {
    @NotNull(message = "La matrícula del odontólogo no puede ser nulo")
    @Max(value = 10, message = "El número de matrícula debe ser menor o igual a 10")
    private int numeroMatricula;

    @NotNull(message = "El nombre del odontólogo no puede ser nulo")
    @NotBlank(message = "Debe especificarse el nombre del odontólogo")
    @Size(max = 50, message = "El nombre debe tener hasta 50 caracteres")
    private String nombre;

    @Size(max = 50, message = "El apellido debe tener hasta 50 caracteres")
    @NotNull(message = "El apellido del odontólogo no puede ser nulo")
    @NotBlank(message = "Debe especificarse el apellido del odontólogo")
    private String apellido;

    public OdontologoEntradaDto() {
    }

    public OdontologoEntradaDto(int numeroMatricula, String nombre, String apellido) {
        this.numeroMatricula = numeroMatricula;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public int getNumeroMatricula() {
        return numeroMatricula;
    }

    public void setNumeroMatricula(int numeroMatricula) {
        this.numeroMatricula = numeroMatricula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
}
