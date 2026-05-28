package com.guanavet.veterinaria.repository;

import com.guanavet.veterinaria.model.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MascotaRepository extends JpaRepository<Mascota,Integer> {

    List<Mascota> findByClienteIdCliente(Integer idCliente);

}