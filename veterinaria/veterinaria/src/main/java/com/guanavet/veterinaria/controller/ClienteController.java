package com.guanavet.veterinaria.controller;

import com.guanavet.veterinaria.model.Cliente;
import com.guanavet.veterinaria.repository.ClienteRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ClienteController {

    private final ClienteRepository clienteRepository;

    public ClienteController(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    // Mostrar lista de clientes
    @GetMapping("/clientes")
    public String listarClientes(Model model) {
        model.addAttribute("clientes", clienteRepository.findAll());
        return "clientes";
    }

    // Mostrar formulario para agregar cliente
    @GetMapping("/clientes/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "form_cliente";
    }

    // Guardar cliente
    @PostMapping("/clientes/guardar")
    public String guardarCliente(@ModelAttribute Cliente cliente) {
        clienteRepository.save(cliente);
        return "redirect:/clientes";
    }

    // Eliminar cliente
    @GetMapping("/clientes/eliminar/{id}")
    public String eliminarCliente(@PathVariable Integer id) {
        clienteRepository.deleteById(id);
        return "redirect:/clientes";
    }

    // Mostrar formulario para editar
    @GetMapping("/clientes/editar/{id}")
    public String editarCliente(@PathVariable Integer id, Model model) {
        Cliente cliente = clienteRepository.findById(id).orElse(null);
        model.addAttribute("cliente", cliente);
        return "form_cliente";
    }
}