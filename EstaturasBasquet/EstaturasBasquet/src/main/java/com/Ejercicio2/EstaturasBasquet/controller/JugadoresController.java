package com.Ejercicio2.EstaturasBasquet.controller;

import com.Ejercicio2.EstaturasBasquet.model.Jugador;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JugadoresController {
    
    
    @PostMapping ("/jugadores")
    public String calcularPromedioEstatura (@RequestBody List<Jugador> listaJugadores) {
    
        double suma_estaturas=0.0;
        double promedio_estatura=0.0;
        
        //guardamos la lista en nuestra base de datos "lógica" en forma de ArrayList 
        //para cumplir con lo que pide el POST, que es un alta
        List<Jugador> listaJuga = new ArrayList<Jugador>();
        listaJuga = listaJugadores;
        
        //sumamos las estaturas
        for (Jugador jug : listaJuga) {
            suma_estaturas = suma_estaturas + jug.getEstatura();
        }
        
        promedio_estatura = suma_estaturas / (listaJuga.size());
        
        //como "response" al alta, otorgamos como dato la estatura de los jugadores
        return "Jugadores registrados correctamente. El promedio de estatura de los mismos es: " + promedio_estatura;
    }
    
    //SUSCRIBITE AL CANAL DE YOUTUBE DE TODOCODE ;)
    
}
