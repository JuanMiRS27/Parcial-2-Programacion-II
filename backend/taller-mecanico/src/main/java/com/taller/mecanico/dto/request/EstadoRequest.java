package com.taller.mecanico.dto.request;

import com.taller.mecanico.model.EstadoCarro;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstadoRequest {
    @NotNull
    private EstadoCarro estado;
}
