package com.guanavet.veterinaria.repository;

import com.guanavet.veterinaria.model.Cita;
import com.guanavet.veterinaria.model.Veterinario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface CitaRepository extends JpaRepository<Cita,Integer> {

    List<Cita> findByEstado(String estado);

    List<Cita> findByMascotaIdMascota(Integer idMascota);

    boolean existsByVeterinarioAndFechaAndHoraAndEstadoNotIn(
            Veterinario veterinario,
            LocalDate fecha,
            LocalTime hora,
            List<String> estados
    );

    boolean existsByVeterinarioAndFechaAndHoraAndEstadoNotInAndIdCitaNot(
            Veterinario veterinario,
            LocalDate fecha,
            LocalTime hora,
            List<String> estados,
            Integer idCita
    );
}