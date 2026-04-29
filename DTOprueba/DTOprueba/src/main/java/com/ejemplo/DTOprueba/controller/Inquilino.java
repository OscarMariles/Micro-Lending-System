package com.ejemplo.DTOprueba.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Inquilino {
    private Long id_inquilino;
    private String dni;
    private String nombre;
    private String apellido;
    private String profesion;    
}
