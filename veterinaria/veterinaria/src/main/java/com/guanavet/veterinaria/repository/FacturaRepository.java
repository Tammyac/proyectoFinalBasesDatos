package com.guanavet.veterinaria.repository;

import com.guanavet.veterinaria.model.Factura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacturaRepository extends JpaRepository<Factura, Integer> {
}