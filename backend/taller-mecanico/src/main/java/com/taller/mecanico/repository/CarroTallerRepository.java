package com.taller.mecanico.repository;

import com.taller.mecanico.model.CarroTaller;
import com.taller.mecanico.model.EstadoCarro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CarroTallerRepository extends JpaRepository<CarroTaller, Long> {
    boolean existsByPlaca(String placa);
    List<CarroTaller> findByEstado(EstadoCarro estado);
    Optional<CarroTaller> findByPlaca(String placa);
}
