package com.guanavet.veterinaria.model;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name="cita")
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_cita")
    private Integer idCita;

    @DateTimeFormat(
            iso = DateTimeFormat.ISO.DATE)
    private LocalDate fecha;

    @DateTimeFormat(
            iso = DateTimeFormat.ISO.TIME)
    private LocalTime hora;

    private String motivo;

    private String estado;


    @ManyToOne
    @JoinColumn(name="id_cliente")
    private Cliente cliente;


    @ManyToOne
    @JoinColumn(name="id_mascota")
    private Mascota mascota;


    @ManyToOne
    @JoinColumn(name="id_veterinario")
    private Veterinario veterinario;


    public Cita(){}


    public Integer getIdCita() {
        return idCita;
    }

    public void setIdCita(Integer idCita) {
        this.idCita = idCita;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Mascota getMascota() {
        return mascota;
    }

    public void setMascota(Mascota mascota) {
        this.mascota = mascota;
    }

    public Veterinario getVeterinario() {
        return veterinario;
    }

    public void setVeterinario(
            Veterinario veterinario) {

        this.veterinario = veterinario;
    }

}