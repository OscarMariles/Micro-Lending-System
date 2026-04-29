package com.gasolinera.ejercicioIntegrador1.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IntegradorController {
    
    @GetMapping("/convertir")
        public String galonesALitros(@RequestParam double galones){
        double litros=galones*3.78541;
        
        return "La cantidad de litros equivalente a: "+galones+" galones es de "+litros+" litros";
    }
        
            
    @GetMapping("/convertir/{galones}")
        public String galonesALitros2(@PathVariable double galones){
        double litros=galones*3.78541;
        
        return "La cantidad de litros equivalente a: "+galones+" galones es de "+litros+" litros";
    }
}

