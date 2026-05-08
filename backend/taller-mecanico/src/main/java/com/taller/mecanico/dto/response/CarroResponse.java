package com.taller.mecanico.dto.response;

import com.taller.mecanico.model.EstadoCarro;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class CarroResponse {
    private Long id;
    private String placa;
    private String modelo;
    private LocalDate fechaIngreso;
    private EstadoCarro estado;
    private String tipoDanio;
}
