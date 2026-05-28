package com.guanavet.veterinaria.controller;

import com.guanavet.veterinaria.model.Consulta;
import com.guanavet.veterinaria.repository.ConsultaRepository;
import com.guanavet.veterinaria.repository.MascotaRepository;
import com.guanavet.veterinaria.repository.VeterinarioRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ConsultaController {

    private final ConsultaRepository consultaRepository;
    private final MascotaRepository mascotaRepository;
    private final VeterinarioRepository veterinarioRepository;

    public ConsultaController(
            ConsultaRepository consultaRepository,
            MascotaRepository mascotaRepository,
            VeterinarioRepository veterinarioRepository){

        this.consultaRepository = consultaRepository;
        this.mascotaRepository = mascotaRepository;
        this.veterinarioRepository = veterinarioRepository;
    }

    @GetMapping("/consultas")
    public String listar(Model model){
        model.addAttribute("consultas", consultaRepository.findAll());
        return "consultas";
    }

    @GetMapping("/consultas/nuevo")
    public String nuevo(Model model){
        model.addAttribute("consulta", new Consulta());
        model.addAttribute("mascotas", mascotaRepository.findAll());
        model.addAttribute("veterinarios", veterinarioRepository.findAll());
        return "form_consulta";
    }

    @PostMapping("/consultas/guardar")
    public String guardar(@ModelAttribute Consulta consulta){
        consultaRepository.save(consulta);
        return "redirect:/consultas";
    }

    @GetMapping("/consultas/editar/{id}")
    public String editar(@PathVariable Integer id, Model model){

        Consulta consulta = consultaRepository.findById(id).orElse(null);

        model.addAttribute("consulta", consulta);
        model.addAttribute("mascotas", mascotaRepository.findAll());
        model.addAttribute("veterinarios", veterinarioRepository.findAll());

        return "form_consulta";
    }

    @GetMapping("/consultas/eliminar/{id}")
    public String eliminar(@PathVariable Integer id){
        consultaRepository.deleteById(id);
        return "redirect:/consultas";
    }
}