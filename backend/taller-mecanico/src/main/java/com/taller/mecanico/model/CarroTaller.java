package com.taller.mecanico.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "carros_taller")
@Getter
@Setter
public class CarroTaller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String placa;
    @Column(nullable = false)
    private String modelo;
    @Column(nullable = false)
    private LocalDate fechaIngreso;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoCarro estado;
    @Column(nullable = false)
    private String tipoDanio;
}
