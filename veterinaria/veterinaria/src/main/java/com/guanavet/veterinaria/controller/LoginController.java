package com.guanavet.veterinaria.controller;

import com.guanavet.veterinaria.model.Usuario;
import com.guanavet.veterinaria.model.Rol;
import org.springframework.ui.Model;
import com.guanavet.veterinaria.model.Usuario;
import com.guanavet.veterinaria.repository.UsuarioRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    private final UsuarioRepository usuarioRepository;

    public LoginController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping("/")
    public String inicio() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String mostrarLogin() {
        return "login";
    }

    @PostMapping("/login")
    public String procesarLogin(@RequestParam String correo,
                                @RequestParam String password,
                                HttpSession session) {

        Usuario usuario = usuarioRepository.findByCorreoAndPassword(correo, password);

        if (usuario != null) {
            session.setAttribute("usuario", usuario);
            session.setAttribute("rol", usuario.getRol().name());
            return "redirect:/menu";
        }

        return "redirect:/login?error";
    }

    @GetMapping("/menu")
    public String menu(HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/login";
        }
        return "menu";
    }

    @GetMapping("/logout")
    public String salir(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    @GetMapping("/registro")
    public String mostrarRegistro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "registro";
    }

    @PostMapping("/registro")
    public String registrarUsuario(@ModelAttribute Usuario usuario) {

        usuario.setRol(Rol.RECEPCIONISTA);

        usuarioRepository.save(usuario);

        return "redirect:/login?registrado";
    }
}
