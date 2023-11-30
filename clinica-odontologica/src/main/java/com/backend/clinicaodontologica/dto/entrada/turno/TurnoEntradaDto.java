package com.backend.clinicaodontologica.dto.entrada.turno;

import com.backend.clinicaodontologica.dto.salida.odontologo.OdontologoSalidaDto;
import com.backend.clinicaodontologica.dto.salida.paciente.PacienteSalidaDto;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.Valid;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class TurnoEntradaDto {
    @FutureOrPresent(message = "La fecha no puede ser anterior al día de hoy")
    @NotNull(message = "Debe especificarse la fecha del turno")
    //@JsonProperty("fecha_ingreso")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime fechaYHora;
    @NotNull(message = "El odontólogo no puede ser nulo")
    @Valid
    private OdontologoSalidaDto odontologoSalidaDto;
    @NotNull(message = "El pacienteSalidaDto no puede ser nulo")
    @Valid
    private PacienteSalidaDto pacienteSalidaDto;

    public TurnoEntradaDto(LocalDateTime fechaYHora, OdontologoSalidaDto odontologoSalidaDto, PacienteSalidaDto pacienteSalidaDto) {
        this.fechaYHora = fechaYHora;
        this.odontologoSalidaDto = odontologoSalidaDto;
        this.pacienteSalidaDto = pacienteSalidaDto;
    }

    public TurnoEntradaDto() {
    }

    public LocalDateTime getFechaYHora() {
        return fechaYHora;
    }

    public void setFechaYHora(LocalDateTime fechaYHora) {
        this.fechaYHora = fechaYHora;
    }

    public OdontologoSalidaDto getOdontologoSalidaDto() {
        return odontologoSalidaDto;
    }

    public void setOdontologoSalidaDto(OdontologoSalidaDto odontologoSalidaDto) {
        this.odontologoSalidaDto = odontologoSalidaDto;
    }

    public PacienteSalidaDto getPacienteSalidaDto() {
        return pacienteSalidaDto;
    }

    public void setPacienteSalidaDto(PacienteSalidaDto pacienteSalidaDto) {
        this.pacienteSalidaDto = pacienteSalidaDto;
    }
}
