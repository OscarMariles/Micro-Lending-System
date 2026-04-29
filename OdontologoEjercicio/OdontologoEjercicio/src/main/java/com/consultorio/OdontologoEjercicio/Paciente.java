package com.consultorio.OdontologoEjercicio;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Paciente {
    private int id;
    private String dni;
    private String nombre;
    private String apellido;
    private LocalDate fechaNac;
}
