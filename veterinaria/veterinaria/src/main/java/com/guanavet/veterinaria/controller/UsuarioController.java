package com.guanavet.veterinaria.controller;

import com.guanavet.veterinaria.model.Rol;
import com.guanavet.veterinaria.model.Usuario;
import com.guanavet.veterinaria.repository.UsuarioRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;

    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    private boolean esAdmin(HttpSession session) {
        Object rol = session.getAttribute("rol");
        return rol != null && rol.toString().equals("ADMIN");
    }

    @GetMapping("/usuarios")
    public String listarUsuarios(Model model, HttpSession session) {

        if (!esAdmin(session)) {
            return "redirect:/menu";
        }

        model.addAttribute("usuarios", usuarioRepository.findAll());

        return "usuarios";
    }

    @GetMapping("/usuarios/nuevo")
    public String nuevoUsuario(Model model, HttpSession session) {

        if (!esAdmin(session)) {
            return "redirect:/menu";
        }

        model.addAttribute("usuario", new Usuario());
        model.addAttribute("roles", Rol.values());

        return "form_usuario";
    }

    @PostMapping("/usuarios/guardar")
    public String guardarUsuario(@ModelAttribute Usuario usuario,
                                 HttpSession session) {

        if (!esAdmin(session)) {
            return "redirect:/menu";
        }

        if (usuario.getRol() == null) {
            usuario.setRol(Rol.RECEPCIONISTA);
        }

        usuarioRepository.save(usuario);

        return "redirect:/usuarios";
    }

    @GetMapping("/usuarios/editar/{id}")
    public String editarUsuario(@PathVariable Integer id,
                                Model model,
                                HttpSession session) {

        if (!esAdmin(session)) {
            return "redirect:/menu";
        }

        Usuario usuario = usuarioRepository.findById(id).orElse(null);

        if (usuario == null) {
            return "redirect:/usuarios";
        }

        model.addAttribute("usuario", usuario);
        model.addAttribute("roles", Rol.values());

        return "form_usuario";
    }

    @GetMapping("/usuarios/eliminar/{id}")
    public String eliminarUsuario(@PathVariable Integer id,
                                  HttpSession session) {

        if (!esAdmin(session)) {
            return "redirect:/menu";
        }

        usuarioRepository.deleteById(id);

        return "redirect:/usuarios";
    }
}