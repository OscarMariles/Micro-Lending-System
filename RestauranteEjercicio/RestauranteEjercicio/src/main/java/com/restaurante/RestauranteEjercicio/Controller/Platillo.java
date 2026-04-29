package com.restaurante.RestauranteEjercicio.Controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Platillo {
    private int numero;
    private String nombre;
    private double precio;
    private String descripcion;
}
