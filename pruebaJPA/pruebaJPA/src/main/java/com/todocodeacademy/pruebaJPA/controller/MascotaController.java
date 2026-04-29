package com.todocodeacademy.pruebaJPA.controller;

import com.todocodeacademy.pruebaJPA.model.Mascota;
import com.todocodeacademy.pruebaJPA.service.IMascotaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MascotaController {
    
   @Autowired
   IMascotaService mascoServ;
           
    //EndPoint para obtener todas las personas
    @GetMapping("/mascotas/traer")
    public List<Mascota> getMascotas(){
        return mascoServ.getMascotas();
    }
    
     //EndPoint para crear una nueva persona
    @PostMapping("/mascotas/crear")
    public String createMascota(@RequestBody Mascota masco){
        mascoServ.saveMascota(masco);
        return "La mascota fue creada correctamente";
    }
}
