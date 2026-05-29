package com.guanavet.veterinaria.controller;

import com.guanavet.veterinaria.model.Servicio;
import com.guanavet.veterinaria.repository.ServicioRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ServicioController {

    private final ServicioRepository servicioRepository;

    public ServicioController(ServicioRepository servicioRepository) {
        this.servicioRepository = servicioRepository;
    }

    @GetMapping("/servicios")
    public String listar(Model model) {
        model.addAttribute("servicios", servicioRepository.findAll());
        return "servicios";
    }

    @GetMapping("/servicios/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("servicio", new Servicio());
        return "form_servicio";
    }

    @PostMapping("/servicios/guardar")
    public String guardar(@ModelAttribute Servicio servicio) {
        servicioRepository.save(servicio);
        return "redirect:/servicios";
    }

    @GetMapping("/servicios/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        Servicio servicio = servicioRepository.findById(id).orElse(null);
        model.addAttribute("servicio", servicio);
        return "form_servicio";
    }

    @GetMapping("/servicios/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        servicioRepository.deleteById(id);
        return "redirect:/servicios";
    }
}