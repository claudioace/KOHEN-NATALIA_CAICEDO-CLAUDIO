package com.backend.clinicaodontologica.controller;


import com.backend.clinicaodontologica.dto.entrada.paciente.PacienteEntradaDto;
import com.backend.clinicaodontologica.dto.salida.paciente.PacienteSalidaDto;
import com.backend.clinicaodontologica.model.Paciente;
import com.backend.clinicaodontologica.service.IPacienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    private IPacienteService pacienteService;

    public PacienteController(IPacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

        //POST
        @PostMapping("/registrar")
        public ResponseEntity<PacienteSalidaDto> registrarPaciente(@RequestBody @Valid PacienteEntradaDto paciente){
            return new ResponseEntity<>(pacienteService.registrarPaciente(paciente), HttpStatus.CREATED);
        }

        //GET
        @GetMapping("/id/{id}")
        public ResponseEntity<PacienteSalidaDto> obtenerPacientePorId(@PathVariable int id){
            return new ResponseEntity<>(pacienteService.buscarPacientePorId(id), HttpStatus.OK);

        }
        @GetMapping("/listar")
        public ResponseEntity<List<PacienteSalidaDto>> listarPacientes(){
            return new ResponseEntity<>(pacienteService.listarPacientes(), OK);
        }



    //PUT
    @PutMapping("/actualizar")
    public ResponseEntity<PacienteSalidaDto> actualizarPaciente(@RequestBody PacienteEntradaDto paciente){
        return new ResponseEntity<>(pacienteService.actualizarPaciente(paciente), OK);
    }
}


