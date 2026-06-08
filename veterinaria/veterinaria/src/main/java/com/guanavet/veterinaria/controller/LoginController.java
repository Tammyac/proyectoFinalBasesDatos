package com.guanavet.veterinaria.controller;

import com.guanavet.veterinaria.model.Rol;
import com.guanavet.veterinaria.model.Usuario;
import com.guanavet.veterinaria.repository.UsuarioRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public LoginController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
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

        Usuario usuario = usuarioRepository.findByCorreo(correo);

        if (usuario == null) {
            return "redirect:/login?error";
        }

        String passwordGuardada = usuario.getPassword();

        boolean loginCorrecto = false;

        /*
         * Caso 1:
         * Si la contraseña ya está cifrada con BCrypt,
         * se compara usando passwordEncoder.matches().
         */
        if (esPasswordCifrada(passwordGuardada)) {
            loginCorrecto = passwordEncoder.matches(password, passwordGuardada);
        }

        /*
         * Caso 2:
         * Si todavía está en texto plano, por ejemplo "1234",
         * se permite entrar una vez y luego se actualiza automáticamente
         * guardándola cifrada en la base de datos.
         */
        else {
            loginCorrecto = passwordGuardada.equals(password);

            if (loginCorrecto) {
                usuario.setPassword(passwordEncoder.encode(password));
                usuarioRepository.save(usuario);
            }
        }

        if (loginCorrecto) {
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

        /*
         * Antes de guardar el usuario,
         * la contraseña se cifra con BCrypt.
         */
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));

        usuarioRepository.save(usuario);

        return "redirect:/login?registrado";
    }

    private boolean esPasswordCifrada(String password) {
        return password != null &&
                (password.startsWith("$2a$")
                        || password.startsWith("$2b$")
                        || password.startsWith("$2y$"));
    }
}