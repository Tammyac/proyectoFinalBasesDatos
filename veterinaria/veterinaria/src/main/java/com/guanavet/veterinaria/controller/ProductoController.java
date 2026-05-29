package com.guanavet.veterinaria.controller;

import com.guanavet.veterinaria.model.Producto;
import com.guanavet.veterinaria.repository.ProductoRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProductoController {

    private final ProductoRepository productoRepository;

    public ProductoController(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @GetMapping("/productos")
    public String listar(Model model){

        model.addAttribute(
                "productos",
                productoRepository.findAll()
        );

        return "productos";
    }

    @GetMapping("/productos/nuevo")
    public String nuevo(Model model){

        model.addAttribute(
                "producto",
                new Producto()
        );

        return "form_producto";
    }

    @PostMapping("/productos/guardar")
    public String guardar(
            @ModelAttribute Producto producto){

        productoRepository.save(producto);

        return "redirect:/productos";
    }

    @GetMapping("/productos/editar/{id}")
    public String editar(
            @PathVariable Integer id,
            Model model){

        Producto producto =
                productoRepository.findById(id).orElse(null);

        model.addAttribute(
                "producto",
                producto
        );

        return "form_producto";
    }

    @GetMapping("/productos/eliminar/{id}")
    public String eliminar(
            @PathVariable Integer id){

        productoRepository.deleteById(id);

        return "redirect:/productos";
    }
}