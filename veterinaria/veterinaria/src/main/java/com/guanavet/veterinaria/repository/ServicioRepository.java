package com.guanavet.veterinaria.repository;

import com.guanavet.veterinaria.model.Servicio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicioRepository extends JpaRepository<Servicio, Integer> {
}