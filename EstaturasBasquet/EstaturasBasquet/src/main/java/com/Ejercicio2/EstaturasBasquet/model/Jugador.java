
package com.Ejercicio2.EstaturasBasquet.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Jugador{    
    
    private Long id;
    private String dni;
    private String nombre;
    private String apellido;
    private int edad;
    private double peso;
    private double estatura;
}
