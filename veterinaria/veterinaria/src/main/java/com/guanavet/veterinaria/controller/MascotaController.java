package com.guanavet.veterinaria.controller;

import com.guanavet.veterinaria.model.Mascota;
import com.guanavet.veterinaria.repository.ClienteRepository;
import com.guanavet.veterinaria.repository.MascotaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MascotaController {

    private final MascotaRepository mascotaRepository;
    private final ClienteRepository clienteRepository;

    public MascotaController(
            MascotaRepository mascotaRepository,
            ClienteRepository clienteRepository) {

        this.mascotaRepository = mascotaRepository;
        this.clienteRepository = clienteRepository;
    }

    // Lista de mascotas
    @GetMapping("/mascotas")
    public String listar(Model model){

        model.addAttribute(
                "mascotas",
                mascotaRepository.findAll()
        );

        return "mascotas";
    }

    // Abrir formulario nuevo
    @GetMapping("/mascotas/nuevo")
    public String nuevo(Model model){

        model.addAttribute(
                "mascota",
                new Mascota()
        );

        model.addAttribute(
                "clientes",
                clienteRepository.findAll()
        );

        return "form_mascota";
    }

    // Guardar mascota
    @PostMapping("/mascotas/guardar")
    public String guardar(
            @ModelAttribute Mascota mascota){

        mascotaRepository.save(mascota);

        return "redirect:/mascotas";
    }

    // Eliminar mascota
    @GetMapping("/mascotas/eliminar/{id}")
    public String eliminar(
            @PathVariable Integer id){

        mascotaRepository.deleteById(id);

        return "redirect:/mascotas";
    }

    // Editar mascota
    @GetMapping("/mascotas/editar/{id}")
    public String editar(
            @PathVariable Integer id,
            Model model){

        Mascota mascota =
                mascotaRepository
                        .findById(id)
                        .orElse(null);

        model.addAttribute(
                "mascota",
                mascota
        );

        model.addAttribute(
                "clientes",
                clienteRepository.findAll()
        );

        return "form_mascota";
    }

}