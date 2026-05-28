package com.guanavet.veterinaria.controller;

import com.guanavet.veterinaria.model.Veterinario;
import com.guanavet.veterinaria.repository.VeterinarioRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class VeterinarioController {

    private final VeterinarioRepository veterinarioRepository;

    public VeterinarioController(VeterinarioRepository veterinarioRepository) {
        this.veterinarioRepository = veterinarioRepository;
    }

    @GetMapping("/veterinarios")
    public String listar(Model model) {
        model.addAttribute("veterinarios", veterinarioRepository.findAll());
        return "veterinarios";
    }

    @GetMapping("/veterinarios/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("veterinario", new Veterinario());
        return "form_veterinario";
    }

    @PostMapping("/veterinarios/guardar")
    public String guardar(@ModelAttribute Veterinario veterinario) {
        veterinarioRepository.save(veterinario);
        return "redirect:/veterinarios";
    }

    @GetMapping("/veterinarios/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        Veterinario veterinario = veterinarioRepository.findById(id).orElse(null);
        model.addAttribute("veterinario", veterinario);
        return "form_veterinario";
    }

    @GetMapping("/veterinarios/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        veterinarioRepository.deleteById(id);
        return "redirect:/veterinarios";
    }
}