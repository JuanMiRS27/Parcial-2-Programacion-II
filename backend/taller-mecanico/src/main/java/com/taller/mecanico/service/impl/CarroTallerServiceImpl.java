package com.taller.mecanico.service.impl;

import com.taller.mecanico.dto.request.CarroRequest;
import com.taller.mecanico.dto.response.CarroResponse;
import com.taller.mecanico.exception.BadRequestException;
import com.taller.mecanico.exception.ResourceNotFoundException;
import com.taller.mecanico.mapper.CarroMapper;
import com.taller.mecanico.model.CarroTaller;
import com.taller.mecanico.model.EstadoCarro;
import com.taller.mecanico.repository.CarroTallerRepository;
import com.taller.mecanico.service.CarroTallerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarroTallerServiceImpl implements CarroTallerService {
    private final CarroTallerRepository repository;
    private final CarroMapper mapper;

    @Override
    public List<CarroResponse> findAll() {
        return repository.findAll().stream().map(mapper::toResponse).toList();
    }

    @Override
    public CarroResponse findById(Long id) {
        return mapper.toResponse(repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Carro no encontrado")));
    }

    @Override
    public CarroResponse create(CarroRequest request) {
        if (repository.existsByPlaca(request.getPlaca())) {
            throw new BadRequestException("La placa ya existe");
        }
        CarroTaller c = new CarroTaller();
        mapper.updateEntity(c, request);
        return mapper.toResponse(repository.save(c));
    }

    @Override
    public CarroResponse update(Long id, CarroRequest request) {
        CarroTaller c = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Carro no encontrado"));
        mapper.updateEntity(c, request);
        return mapper.toResponse(repository.save(c));
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Carro no encontrado");
        }
        repository.deleteById(id);
    }

    @Override
    public List<CarroResponse> findByEstado(EstadoCarro estado) {
        return repository.findByEstado(estado).stream().map(mapper::toResponse).toList();
    }

    @Override
    public CarroResponse findByPlaca(String placa) {
        return mapper.toResponse(repository.findByPlaca(placa)
                .orElseThrow(() -> new ResourceNotFoundException("Carro no encontrado")));
    }
}
