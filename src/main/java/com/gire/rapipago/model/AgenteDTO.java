package com.gire.rapipago.model;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class AgenteDTO {

    private Long id;

    private String nombre;

    private Integer codigo;

    //private List<Sucursal> sucursales

    public AgenteDTO(String nombre, Integer codigo) {
        this.nombre = nombre;
        this.codigo = codigo;
    }

    public AgenteDTO() {

    }


}
