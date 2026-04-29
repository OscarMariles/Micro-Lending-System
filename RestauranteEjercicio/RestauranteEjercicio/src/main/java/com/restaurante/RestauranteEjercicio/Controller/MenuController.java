package com.restaurante.RestauranteEjercicio.Controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MenuController {
    List<Platillo> listaPlatillo=new ArrayList<Platillo>();
    
    @GetMapping("/menu/platillo")
    @ResponseBody
    public Platillo consulta(@RequestParam int numero){
        
        listaPlatillo.add(new Platillo(1, "Milanesa", 99.99, "Pechuga de Pollo empanizada con papas a la francesa"));
        listaPlatillo.add(new Platillo(2, "Tacos Suadero", 50, "Orden de 4 Tacos de Suadero"));
        listaPlatillo.add(new Platillo(3, "Ensalada de Atun", 70, "Ensalada de atun saludable"));
        
        for (Platillo pla:listaPlatillo){
            if (pla.getNumero()==numero){
                return pla;
            }
        }
        return null;
    }
    
}
