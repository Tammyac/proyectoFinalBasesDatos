package com.guanavet.veterinaria.repository;

import com.guanavet.veterinaria.model.VacunaMascota;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VacunaMascotaRepository
        extends JpaRepository<VacunaMascota,Integer>{

    List<VacunaMascota> findByMascotaIdMascota(
            Integer idMascota
    );

}