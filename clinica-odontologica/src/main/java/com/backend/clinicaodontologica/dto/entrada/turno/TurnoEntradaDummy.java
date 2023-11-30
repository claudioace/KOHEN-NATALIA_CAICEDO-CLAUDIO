package com.backend.clinicaodontologica.dto.entrada.turno;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.Valid;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class TurnoEntradaDummy {
    @FutureOrPresent(message = "La fecha no puede ser anterior al día de hoy")
    @NotNull(message = "Debe especificarse la fecha del turno")
    //@JsonProperty("fecha_ingreso")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime fechaYHora;
    @NotNull(message = "El odontólogo no puede ser nulo")
    @Valid
    private Long idOdontologoSalidaDto;
    @NotNull(message = "El paciente no puede ser nulo")
    @Valid
    private Long idPacienteSalidaDto;

    public TurnoEntradaDummy(LocalDateTime fechaYHora, Long idOdontologoSalidaDto, Long idPacienteSalidaDto) {
        this.fechaYHora = fechaYHora;
        this.idOdontologoSalidaDto = idOdontologoSalidaDto;
        this.idPacienteSalidaDto = idPacienteSalidaDto;
    }

    public TurnoEntradaDummy() {
    }

    public LocalDateTime getFechaYHora() {
        return fechaYHora;
    }

    public void setFechaYHora(LocalDateTime fechaYHora) {
        this.fechaYHora = fechaYHora;
    }

    public Long getIdOdontologoSalidaDto() {
        return idOdontologoSalidaDto;
    }

    public void setIdOdontologoSalidaDto(Long idOdontologoSalidaDto) {
        this.idOdontologoSalidaDto = idOdontologoSalidaDto;
    }

    public Long getIdPacienteSalidaDto() {
        return idPacienteSalidaDto;
    }

    public void setIdPacienteSalidaDto(Long idPacienteSalidaDto) {
        this.idPacienteSalidaDto = idPacienteSalidaDto;
    }
}
