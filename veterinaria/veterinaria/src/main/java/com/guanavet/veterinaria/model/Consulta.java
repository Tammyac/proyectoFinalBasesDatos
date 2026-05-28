package com.guanavet.veterinaria.model;

import jakarta.persistence.*;

@Entity
@Table(name="consulta")
public class Consulta {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_consulta")
    private Integer idConsulta;

    private String diagnostico;

    private String tratamiento;

    @ManyToOne
    @JoinColumn(name="id_mascota")
    private Mascota mascota;

    @ManyToOne
    @JoinColumn(name="id_veterinario")
    private Veterinario veterinario;

    public Consulta(){}

    public Integer getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(Integer idConsulta) {
        this.idConsulta = idConsulta;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
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

    public void setVeterinario(Veterinario veterinario) {
        this.veterinario = veterinario;
    }
}