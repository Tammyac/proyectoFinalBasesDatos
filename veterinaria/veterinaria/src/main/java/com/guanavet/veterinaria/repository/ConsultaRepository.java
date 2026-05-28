package com.guanavet.veterinaria.repository;

import com.guanavet.veterinaria.model.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConsultaRepository
        extends JpaRepository<Consulta,Integer>{

    List<Consulta> findByMascotaIdMascota(Integer idMascota);

}