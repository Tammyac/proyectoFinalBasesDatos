package com.guanavet.veterinaria.repository;

import com.guanavet.veterinaria.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Usuario findByCorreoAndPassword(String correo, String password);
}