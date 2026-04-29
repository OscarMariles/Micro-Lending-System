package com.ejemplo.DTOprueba.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PropiedadDTO {
    private Long id_propiedad;
    private String tipo_propiedad;
    private String direccion;
    private Double valor_alquiler;
    private String nombre;
    private String apellido;
}
