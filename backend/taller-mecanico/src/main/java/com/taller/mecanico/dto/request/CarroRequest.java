package com.taller.mecanico.dto.request;

import com.taller.mecanico.model.EstadoCarro;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CarroRequest {
    @NotBlank
    private String placa;
    @NotBlank
    private String modelo;
    @NotNull
    private LocalDate fechaIngreso;
    @NotNull
    private EstadoCarro estado;
    @NotBlank
    private String tipoDanio;
}
