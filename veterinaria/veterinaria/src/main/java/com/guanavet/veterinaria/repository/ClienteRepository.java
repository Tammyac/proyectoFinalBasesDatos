package com.guanavet.veterinaria.repository;

import com.guanavet.veterinaria.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}