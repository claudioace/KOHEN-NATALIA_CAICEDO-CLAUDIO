package com.backend.clinicaodontologica.dto.entrada.turno;


import com.backend.clinicaodontologica.dto.entrada.odontologo.OdontologoEntradaDto;

import com.backend.clinicaodontologica.dto.entrada.paciente.PacienteEntradaDto;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.Valid;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class TurnoEntradaDto {
    @FutureOrPresent(message = "La fecha no puede ser anterior al día de hoy")
    @NotNull(message = "Debe especificarse la fecha del turno")
    //@JsonProperty("fecha_ingreso")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime fechaYHora;
    @NotNull(message = "El odontólogo no puede ser nulo")
    @Valid
    private OdontologoEntradaDto odontologoEntradaDto;
    @NotNull(message = "El pacienteEntradaDto no puede ser nulo")
    @Valid
    private PacienteEntradaDto pacienteEntradaDto;

    public TurnoEntradaDto(LocalDateTime fechaYHora, OdontologoEntradaDto odontologoEntradaDto, PacienteEntradaDto pacienteEntradaDto) {
        this.fechaYHora = fechaYHora;
        this.odontologoEntradaDto = odontologoEntradaDto;
        this.pacienteEntradaDto = pacienteEntradaDto;
    }

    public TurnoEntradaDto() {
    }

    public LocalDateTime getFechaYHora() {
        return fechaYHora;
    }

    public void setFechaYHora(LocalDateTime fechaYHora) {
        this.fechaYHora = fechaYHora;
    }

    public OdontologoEntradaDto getOdontologoEntradaDto() {
        return odontologoEntradaDto;
    }

    public void setOdontologoEntradaDto(OdontologoEntradaDto odontologoEntradaDto) {
        this.odontologoEntradaDto = odontologoEntradaDto;
    }

    public PacienteEntradaDto getPacienteEntradaDto() {
        return pacienteEntradaDto;
    }

    public void setPacienteEntradaDto(PacienteEntradaDto pacienteEntradaDto) {
        this.pacienteEntradaDto = pacienteEntradaDto;
    }
}
