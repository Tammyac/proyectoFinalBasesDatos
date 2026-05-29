package com.guanavet.veterinaria.controller;

import com.guanavet.veterinaria.model.*;
import com.guanavet.veterinaria.repository.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/facturas")
public class FacturaController {

    private final FacturaRepository facturaRepository;
    private final ClienteRepository clienteRepository;
    private final ProductoRepository productoRepository;
    private final ServicioRepository servicioRepository;

    public FacturaController(
            FacturaRepository facturaRepository,
            ClienteRepository clienteRepository,
            ProductoRepository productoRepository,
            ServicioRepository servicioRepository) {

        this.facturaRepository = facturaRepository;
        this.clienteRepository = clienteRepository;
        this.productoRepository = productoRepository;
        this.servicioRepository = servicioRepository;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("facturas", facturaRepository.findAll());
        return "facturas";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("factura", new Factura());
        model.addAttribute("clientes", clienteRepository.findAll());
        model.addAttribute("productos", productoRepository.findAll());
        model.addAttribute("servicios", servicioRepository.findAll());

        return "form_factura";
    }

    @PostMapping("/guardar")
    public String guardar(
            @ModelAttribute Factura factura,
            @RequestParam("tipo") List<String> tipos,
            @RequestParam(value = "productoId", required = false) List<Integer> productoIds,
            @RequestParam(value = "servicioId", required = false) List<Integer> servicioIds,
            @RequestParam("cantidad") List<Integer> cantidades) {

        List<DetalleFactura> detalles = new ArrayList<>();
        double total = 0;

        int productoIndex = 0;
        int servicioIndex = 0;

        for (int i = 0; i < tipos.size(); i++) {

            String tipo = tipos.get(i);
            Integer cantidad = cantidades.get(i);

            DetalleFactura detalle = new DetalleFactura();
            detalle.setFactura(factura);
            detalle.setTipo(tipo);
            detalle.setCantidad(cantidad);

            if (tipo.equals("PRODUCTO")) {

                Integer idProducto = productoIds.get(productoIndex);
                productoIndex++;

                Producto producto = productoRepository.findById(idProducto).orElse(null);

                if (producto != null) {
                    detalle.setProducto(producto);
                    detalle.setDescripcion(producto.getNombre());
                    detalle.setPrecioUnitario(producto.getPrecio());
                    detalle.setSubtotal(producto.getPrecio() * cantidad);

                    producto.setStock(producto.getStock() - cantidad);
                    productoRepository.save(producto);
                }

            } else if (tipo.equals("SERVICIO")) {

                Integer idServicio = servicioIds.get(servicioIndex);
                servicioIndex++;

                Servicio servicio = servicioRepository.findById(idServicio).orElse(null);

                if (servicio != null) {
                    detalle.setServicio(servicio);
                    detalle.setDescripcion(servicio.getNombre());
                    detalle.setPrecioUnitario(servicio.getPrecio());
                    detalle.setSubtotal(servicio.getPrecio() * cantidad);
                }
            }

            total += detalle.getSubtotal();
            detalles.add(detalle);
        }

        factura.setTotal(total);
        factura.setDetalles(detalles);

        facturaRepository.save(factura);

        return "redirect:/facturas";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        facturaRepository.deleteById(id);
        return "redirect:/facturas";
    }
}