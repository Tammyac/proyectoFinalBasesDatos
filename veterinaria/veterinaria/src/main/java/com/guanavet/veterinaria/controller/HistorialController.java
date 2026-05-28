package com.guanavet.veterinaria.controller;

import com.guanavet.veterinaria.model.Mascota;
import com.guanavet.veterinaria.repository.CitaRepository;
import com.guanavet.veterinaria.repository.ConsultaRepository;
import com.guanavet.veterinaria.repository.MascotaRepository;
import com.guanavet.veterinaria.repository.VacunaMascotaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HistorialController {

    private final MascotaRepository mascotaRepository;
    private final CitaRepository citaRepository;
    private final ConsultaRepository consultaRepository;
    private final VacunaMascotaRepository vacunaMascotaRepository;

    public HistorialController(
            MascotaRepository mascotaRepository,
            CitaRepository citaRepository,
            ConsultaRepository consultaRepository,
            VacunaMascotaRepository vacunaMascotaRepository) {

        this.mascotaRepository = mascotaRepository;
        this.citaRepository = citaRepository;
        this.consultaRepository = consultaRepository;
        this.vacunaMascotaRepository = vacunaMascotaRepository;
    }

    @GetMapping("/mascotas/historial/{id}")
    public String historial(@PathVariable Integer id, Model model) {

        Mascota mascota = mascotaRepository.findById(id).orElse(null);

        model.addAttribute("mascota", mascota);
        model.addAttribute("citas", citaRepository.findByMascotaIdMascota(id));
        model.addAttribute("consultas", consultaRepository.findByMascotaIdMascota(id));
        model.addAttribute("vacunasMascota", vacunaMascotaRepository.findByMascotaIdMascota(id));

        return "historial_mascota";
    }
}