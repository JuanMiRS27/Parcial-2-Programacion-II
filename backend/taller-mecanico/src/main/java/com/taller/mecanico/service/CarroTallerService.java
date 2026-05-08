package com.taller.mecanico.service;

import com.taller.mecanico.dto.request.CarroRequest;
import com.taller.mecanico.dto.response.CarroResponse;
import com.taller.mecanico.model.EstadoCarro;

import java.util.List;

public interface CarroTallerService {
    List<CarroResponse> findAll();
    CarroResponse findById(Long id);
    CarroResponse create(CarroRequest request);
    CarroResponse update(Long id, CarroRequest request);
    CarroResponse updateEstado(Long id, EstadoCarro estado);
    void delete(Long id);
    List<CarroResponse> findByEstado(EstadoCarro estado);
    CarroResponse findByPlaca(String placa);
}
