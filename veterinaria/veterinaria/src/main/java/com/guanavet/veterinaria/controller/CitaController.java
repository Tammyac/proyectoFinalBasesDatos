package com.guanavet.veterinaria.controller;

import com.guanavet.veterinaria.model.Cita;
import com.guanavet.veterinaria.model.Mascota;
import com.guanavet.veterinaria.repository.CitaRepository;
import com.guanavet.veterinaria.repository.ClienteRepository;
import com.guanavet.veterinaria.repository.MascotaRepository;
import com.guanavet.veterinaria.repository.VeterinarioRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CitaController {

    private final CitaRepository citaRepository;
    private final ClienteRepository clienteRepository;
    private final MascotaRepository mascotaRepository;
    private final VeterinarioRepository veterinarioRepository;

    public CitaController(
            CitaRepository citaRepository,
            ClienteRepository clienteRepository,
            MascotaRepository mascotaRepository,
            VeterinarioRepository veterinarioRepository) {

        this.citaRepository = citaRepository;
        this.clienteRepository = clienteRepository;
        this.mascotaRepository = mascotaRepository;
        this.veterinarioRepository = veterinarioRepository;
    }

    @GetMapping("/citas")
    public String listar(@RequestParam(required = false) String estado, Model model) {
        if (estado == null || estado.isEmpty()) {
            model.addAttribute("citas", citaRepository.findAll());
        } else {
            model.addAttribute("citas", citaRepository.findByEstado(estado));
        }

        model.addAttribute("estadoSeleccionado", estado);
        return "citas";
    }

    @GetMapping("/citas/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("cita", new Cita());
        model.addAttribute("clientes", clienteRepository.findAll());
        model.addAttribute("veterinarios", veterinarioRepository.findAll());
        return "form_cita";
    }

    @PostMapping("/citas/guardar")
    public String guardar(@ModelAttribute Cita cita, Model model) {

        if (cita.getEstado() == null || cita.getEstado().isEmpty()) {
            cita.setEstado("Pendiente");
        }

        List<String> estadosLibres = List.of("Cancelada", "No asistió");

        boolean horarioOcupado;

        if (cita.getIdCita() == null) {
            horarioOcupado =
                    citaRepository.existsByVeterinarioAndFechaAndHoraAndEstadoNotIn(
                            cita.getVeterinario(),
                            cita.getFecha(),
                            cita.getHora(),
                            estadosLibres
                    );
        } else {
            horarioOcupado =
                    citaRepository.existsByVeterinarioAndFechaAndHoraAndEstadoNotInAndIdCitaNot(
                            cita.getVeterinario(),
                            cita.getFecha(),
                            cita.getHora(),
                            estadosLibres,
                            cita.getIdCita()
                    );
        }

        if (horarioOcupado) {
            model.addAttribute(
                    "error",
                    "Ese veterinario ya tiene una cita activa en esa fecha y hora."
            );

            model.addAttribute("cita", cita);
            model.addAttribute("clientes", clienteRepository.findAll());
            model.addAttribute("veterinarios", veterinarioRepository.findAll());

            if (cita.getCliente() != null) {
                model.addAttribute(
                        "mascotas",
                        mascotaRepository.findByClienteIdCliente(
                                cita.getCliente().getIdCliente()
                        )
                );
            }

            return "form_cita";
        }

        citaRepository.save(cita);
        return "redirect:/citas";
    }

    @GetMapping("/citas/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        Cita cita = citaRepository.findById(id).orElse(null);

        model.addAttribute("cita", cita);
        model.addAttribute("clientes", clienteRepository.findAll());
        model.addAttribute("veterinarios", veterinarioRepository.findAll());

        if (cita != null && cita.getCliente() != null) {
            model.addAttribute(
                    "mascotas",
                    mascotaRepository.findByClienteIdCliente(
                            cita.getCliente().getIdCliente()
                    )
            );
        }

        return "form_cita";
    }

    @GetMapping("/citas/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        citaRepository.deleteById(id);
        return "redirect:/citas";
    }

    @GetMapping("/mascotas/cliente/{id}")
    @ResponseBody
    public List<Mascota> mascotasPorCliente(@PathVariable Integer id) {
        return mascotaRepository.findByClienteIdCliente(id);
    }
}