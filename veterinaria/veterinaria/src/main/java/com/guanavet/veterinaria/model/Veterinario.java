package com.guanavet.veterinaria.model;

import jakarta.persistence.*;

@Entity
@Table(name="veterinario")
public class Veterinario {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_vet")
    private Integer idVeterinario;

    private String nombre;
    private String especialidad;
    private String telefono;
    private String correo;

    public Veterinario(){}

    public Integer getIdVeterinario() {
        return idVeterinario;
    }

    public void setIdVeterinario(Integer idVeterinario) {
        this.idVeterinario = idVeterinario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}