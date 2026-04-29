package com.ejemplo.prueba.Controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {
    private long id;
    private String nombre;
    private String apellido;
}
