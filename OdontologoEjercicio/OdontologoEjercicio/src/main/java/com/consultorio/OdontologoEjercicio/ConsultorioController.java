package com.consultorio.OdontologoEjercicio;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsultorioController {
   List<Paciente> listaPacientes=new ArrayList();

    public ConsultorioController() {
        listaPacientes.add(new Paciente(1,"1234","Oscar","Mariles",LocalDate.of(1999, 04, 04)));
        listaPacientes.add(new Paciente(2,"4321","Diego","Mariles",LocalDate.of(2009, 06, 29)));
        listaPacientes.add(new Paciente(3,"5678","Rosa","Isela",LocalDate.of(2000, 03, 01)));
        listaPacientes.add(new Paciente(4,"8765","Juan","Guzman",LocalDate.of(2013, 11, 20)));
        listaPacientes.add(new Paciente(5,"0987","Lorena","Lopez",LocalDate.of(2016, 05, 14)));
        listaPacientes.add(new Paciente(6,"7890","Joaquin","Loera",LocalDate.of(1980, 10, 22)));
    }
    
    @GetMapping("/consultorio/traer")
    @ResponseBody
    public List<Paciente> getPacientes(){
        return listaPacientes;
    }
   
    @GetMapping("/consultorio/menores")
    public List<Paciente> getMenores(){
        List<Paciente> listaMenores=new ArrayList<>();
        
        for (Paciente p:listaPacientes){
            if (Period.between(p.getFechaNac(), LocalDate.now()).getYears()<18){
                listaMenores.add(p);
            }
        }
        return listaMenores;
    }
   
}
