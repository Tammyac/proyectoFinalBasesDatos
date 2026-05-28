package com.guanavet.veterinaria.repository;

import com.guanavet.veterinaria.model.Veterinario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VeterinarioRepository
        extends JpaRepository<Veterinario,Integer>{

}