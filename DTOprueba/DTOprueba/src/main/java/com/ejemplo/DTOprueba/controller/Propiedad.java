package com.ejemplo.DTOprueba.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Propiedad {
    private Long id_propiedad;
    private String tipo_propiedad;
    private String direccion;
    private Double metros_cuadrados;
    private Double valor_alquiler;
}
