package com.guanavet.veterinaria.controller;

import com.guanavet.veterinaria.model.VacunaMascota;
import com.guanavet.veterinaria.repository.MascotaRepository;
import com.guanavet.veterinaria.repository.VacunaMascotaRepository;
import com.guanavet.veterinaria.repository.VacunaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class VacunaMascotaController {

    private final VacunaMascotaRepository vacunaMascotaRepository;
    private final MascotaRepository mascotaRepository;
    private final VacunaRepository vacunaRepository;

    public VacunaMascotaController(
            VacunaMascotaRepository vacunaMascotaRepository,
            MascotaRepository mascotaRepository,
            VacunaRepository vacunaRepository){

        this.vacunaMascotaRepository = vacunaMascotaRepository;
        this.mascotaRepository = mascotaRepository;
        this.vacunaRepository = vacunaRepository;
    }

    @GetMapping("/vacunasmascota")
    public String listar(Model model){

        model.addAttribute(
                "vacunasMascota",
                vacunaMascotaRepository.findAll()
        );

        return "vacunas_mascota";
    }

    @GetMapping("/vacunasmascota/nuevo")
    public String nuevo(Model model){

        model.addAttribute(
                "vacunaMascota",
                new VacunaMascota()
        );

        model.addAttribute(
                "mascotas",
                mascotaRepository.findAll()
        );

        model.addAttribute(
                "vacunas",
                vacunaRepository.findAll()
        );

        return "form_vacuna_mascota";
    }

    @PostMapping("/vacunasmascota/guardar")
    public String guardar(
            @ModelAttribute
            VacunaMascota vacunaMascota){

        vacunaMascotaRepository.save(
                vacunaMascota
        );

        return "redirect:/vacunasmascota";
    }

    @GetMapping("/vacunasmascota/eliminar/{id}")
    public String eliminar(
            @PathVariable Integer id){

        vacunaMascotaRepository.deleteById(id);

        return "redirect:/vacunasmascota";
    }

}