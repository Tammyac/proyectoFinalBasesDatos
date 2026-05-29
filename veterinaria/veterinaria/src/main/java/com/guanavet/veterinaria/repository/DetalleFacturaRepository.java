package com.guanavet.veterinaria.repository;

import com.guanavet.veterinaria.model.DetalleFactura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetalleFacturaRepository extends JpaRepository<DetalleFactura, Integer> {
}