package com.guanavet.veterinaria.model;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Table(name="vacuna_mascota")
public class VacunaMascota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_vacuna_mascota")
    private Integer idVacunaMascota;

    @ManyToOne
    @JoinColumn(name="id_mascota")
    private Mascota mascota;

    @ManyToOne
    @JoinColumn(name="id_vacuna")
    private Vacuna vacuna;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate fecha;

    public VacunaMascota(){}

    public Integer getIdVacunaMascota() {
        return idVacunaMascota;
    }

    public void setIdVacunaMascota(Integer idVacunaMascota) {
        this.idVacunaMascota = idVacunaMascota;
    }

    public Mascota getMascota() {
        return mascota;
    }

    public void setMascota(Mascota mascota) {
        this.mascota = mascota;
    }

    public Vacuna getVacuna() {
        return vacuna;
    }

    public void setVacuna(Vacuna vacuna) {
        this.vacuna = vacuna;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}