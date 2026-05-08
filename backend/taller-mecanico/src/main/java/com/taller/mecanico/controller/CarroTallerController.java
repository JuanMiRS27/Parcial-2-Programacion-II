package com.taller.mecanico.controller;

import com.taller.mecanico.dto.request.CarroRequest;
import com.taller.mecanico.dto.request.EstadoRequest;
import com.taller.mecanico.dto.response.CarroResponse;
import com.taller.mecanico.model.EstadoCarro;
import com.taller.mecanico.service.CarroTallerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carros")
@RequiredArgsConstructor
public class CarroTallerController {
    private final CarroTallerService service;

    @GetMapping
    public List<CarroResponse> findAll() { return service.findAll(); }

    @GetMapping("/{id}")
    public CarroResponse findById(@PathVariable Long id) { return service.findById(id); }

    @PostMapping
    public CarroResponse create(@Valid @RequestBody CarroRequest request) { return service.create(request); }

    @PutMapping("/{id}")
    public CarroResponse update(@PathVariable Long id, @Valid @RequestBody CarroRequest request) { return service.update(id, request); }

    @PutMapping("/{id}/estado")
    public CarroResponse updateEstado(@PathVariable Long id, @Valid @RequestBody EstadoRequest request) {
        return service.updateEstado(id, request.getEstado());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/estado/{estado}")
    public List<CarroResponse> findByEstado(@PathVariable EstadoCarro estado) { return service.findByEstado(estado); }

    @GetMapping("/placa/{placa}")
    public CarroResponse findByPlaca(@PathVariable String placa) { return service.findByPlaca(placa); }
}
