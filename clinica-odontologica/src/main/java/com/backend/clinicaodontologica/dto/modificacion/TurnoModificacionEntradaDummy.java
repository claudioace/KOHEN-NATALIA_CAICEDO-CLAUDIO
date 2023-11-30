package com.backend.clinicaodontologica.dto.modificacion;

import com.backend.clinicaodontologica.dto.salida.odontologo.OdontologoSalidaDto;
import com.backend.clinicaodontologica.dto.salida.paciente.PacienteSalidaDto;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.Valid;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class TurnoModificacionEntradaDummy {
    @NotNull(message = "Debe proveerse el id del turno que se desea modificar")
    private Long id;
    @FutureOrPresent(message = "La fecha no puede ser anterior al día de hoy")
    @NotNull(message = "Debe especificarse la fecha del turno")
    //@JsonProperty("fecha_ingreso")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime fechaYHora;
    @NotNull(message = "El id odontólogo no puede ser nulo")
    @Valid
    private Long idOdontologoSalidaDto;
    @NotNull(message = "El id paciente no puede ser nulo")
    @Valid
    private Long idPacienteSalidaDto;

    public TurnoModificacionEntradaDummy() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public TurnoModificacionEntradaDummy(Long id, LocalDateTime fechaYHora, Long idOdontologoSalidaDto, Long idPacienteSalidaDto) {
        this.id = id;
        this.fechaYHora = fechaYHora;
        this.idOdontologoSalidaDto = idOdontologoSalidaDto;
        this.idPacienteSalidaDto = idPacienteSalidaDto;
    }
}
