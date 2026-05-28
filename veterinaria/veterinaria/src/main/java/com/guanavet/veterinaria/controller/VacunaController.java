package com.guanavet.veterinaria.controller;

import com.guanavet.veterinaria.model.Vacuna;
import com.guanavet.veterinaria.repository.VacunaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class VacunaController {

    private final VacunaRepository vacunaRepository;

    public VacunaController(VacunaRepository vacunaRepository) {
        this.vacunaRepository = vacunaRepository;
    }

    @GetMapping("/vacunas")
    public String listar(Model model) {
        model.addAttribute("vacunas", vacunaRepository.findAll());
        return "vacunas";
    }

    @GetMapping("/vacunas/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("vacuna", new Vacuna());
        return "form_vacuna";
    }

    @PostMapping("/vacunas/guardar")
    public String guardar(@ModelAttribute Vacuna vacuna) {
        vacunaRepository.save(vacuna);
        return "redirect:/vacunas";
    }

    @GetMapping("/vacunas/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        Vacuna vacuna = vacunaRepository.findById(id).orElse(null);
        model.addAttribute("vacuna", vacuna);
        return "form_vacuna";
    }

    @GetMapping("/vacunas/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        vacunaRepository.deleteById(id);
        return "redirect:/vacunas";
    }
}