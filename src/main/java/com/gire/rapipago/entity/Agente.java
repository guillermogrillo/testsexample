package com.gire.rapipago.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity(name = "RP_AGENTE")
@Data
public class Agente implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private Integer codigo;

    public Agente() {

    }

    public Agente(String nombre, Integer codigo) {
        this.nombre = nombre;
        this.codigo = codigo;
    }

    public Agente(Long id, String nombre, Integer codigo)
    {
        this.id = id;
        this.nombre = nombre;
        this.codigo = codigo;
    }


}
