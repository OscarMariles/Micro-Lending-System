package com.todocodeacademy.pruebaJPA.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Mascota {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id_mascota;
    private String nombre;
    private String especie;
    private String raza;
    private String color;
}
