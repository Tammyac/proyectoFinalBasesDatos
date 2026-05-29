package com.guanavet.veterinaria.repository;

import com.guanavet.veterinaria.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository
        extends JpaRepository<Producto,Integer> {
}