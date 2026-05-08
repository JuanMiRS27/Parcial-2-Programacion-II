package com.taller.mecanico.mapper;

import com.taller.mecanico.dto.request.CarroRequest;
import com.taller.mecanico.dto.response.CarroResponse;
import com.taller.mecanico.model.CarroTaller;
import org.springframework.stereotype.Component;

@Component
public class CarroMapper {
    public CarroResponse toResponse(CarroTaller c) {
        return CarroResponse.builder()
                .id(c.getId()).placa(c.getPlaca()).modelo(c.getModelo())
                .fechaIngreso(c.getFechaIngreso()).estado(c.getEstado()).tipoDanio(c.getTipoDanio()).build();
    }

    public void updateEntity(CarroTaller c, CarroRequest r) {
        c.setPlaca(r.getPlaca());
        c.setModelo(r.getModelo());
        c.setFechaIngreso(r.getFechaIngreso());
        c.setEstado(r.getEstado());
        c.setTipoDanio(r.getTipoDanio());
    }
}
