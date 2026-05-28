package com.guanavet.veterinaria.repository;

import com.guanavet.veterinaria.model.Vacuna;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VacunaRepository extends JpaRepository<Vacuna, Integer> {
}