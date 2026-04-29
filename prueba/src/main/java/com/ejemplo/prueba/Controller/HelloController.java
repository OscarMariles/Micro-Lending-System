
package com.ejemplo.prueba.Controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    
    //@PathBAriable
    
    @GetMapping("/hello/{nombre}")
    public String sayHello(@PathVariable String nombre){
        return "Hola "+nombre;
    }
    
    @GetMapping("/bye/{nombre}/{edad}/{profesion}")
    public String sayBye(@PathVariable String nombre,
                         @PathVariable int edad,
                         @PathVariable String profesion){
        return "Adios Mundo "+"Nombre: "+nombre+ " Edad: "+edad+" Profesion: "+profesion;
        //http://localhost:8080/hello2?nombre=Gordo
    }
    
    //@RequestParam
    
    @GetMapping("/hello2")
    public String sayHelloRequest(@RequestParam String nombre){
        return "Hola "+nombre;
    }
    
    @GetMapping("/bye")
    public String sayByeRequest(@RequestParam String nombre,
                                @RequestParam int edad,
                                @RequestParam String profesion){
        return "Adios Mundo "+"Nombre: "+nombre+ " Edad: "+edad+" Profesion: "+profesion;
        //http://localhost:8080/bye?nombre=Gordo&edad=26&profesion=administrador%20de%20empresas
    }
    
    //Ejercicio 1 Profesor de matematicas
    @GetMapping("/calculo/{numero1}/{numero2}")
    public int calculoSuma(@PathVariable int numero1,@PathVariable int numero2){
        return numero1+numero2;
        //http://localhost:8080/calculo/500/19
    }
    
    @GetMapping("/estadoPeso")
    public String estadoPesoUsuario(@RequestParam double peso){
        if (peso>=1 && peso<=18.5){
            return "Peso Suficiente";
        }else if(peso>=18.6 && peso<=24.9){
            return "Peso Normal";
        }else if (peso>=25.0 && peso<= 29.9){
            return "Sobrepeso";
        }else if (peso>=30){
            return "Obesidad";
        }
        return "no lo se bro";
        
        //http://localhost:8080/estadoPeso?peso=18.6
    }
    
    //@PostMapping
    
    @PostMapping("/cliente")
    public void crearCliente(@RequestBody Cliente cli){
        System.out.println("Cliente creado. Nombre :"+cli.getNombre()+" Apellido :"+cli.getApellido());
    }
    
    //@ResponseBody
    @GetMapping("/cliente/traer")
    @ResponseBody
    public List<Cliente> traerClientes(){
        List<Cliente> listaClientes=new ArrayList<Cliente>();
        
        listaClientes.add(new Cliente(1L,"Zlathn","Ibrahimovic"));
        listaClientes.add(new Cliente(12,"Lionel","Messi"));
        listaClientes.add(new Cliente(13,"Cristiano","Ronaldo"));
        
        return listaClientes;
    }
    
    @GetMapping("/pruebaresponse")
    ResponseEntity<String> traerRespuesta(){
      return new ResponseEntity<>("Esta es una prueba de Response",HttpStatus.NOT_FOUND);
    }
}
