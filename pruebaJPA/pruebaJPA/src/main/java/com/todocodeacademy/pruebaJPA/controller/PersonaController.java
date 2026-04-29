package com.todocodeacademy.pruebaJPA.controller;

import com.todocodeacademy.pruebaJPA.model.Persona;
import com.todocodeacademy.pruebaJPA.service.IPersonaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonaController {
    
    @Autowired
    private IPersonaService persoServ;
    
    //EndPoint para obtener todas las personas
    @GetMapping("/personas/traer")
    public List<Persona> getPersonas(){
        return persoServ.getPersonas();
    }
    
    //EndPoint para crear una nueva persona
    @PostMapping("personas/crear")
    public String createStudent(@RequestBody Persona perso){
        persoServ.savePersona(perso);
        return "La persona fue creada correctamente";
    }
    
    //EndPoint para dar de baja una nueva persona
    @DeleteMapping("/personas/borrar/{id}")
    public String deletePersona(@PathVariable Long id){
        persoServ.deletePersona(id);
        
        return "La persona fue eliminada correctamente";
    } 
    
    //EndPoint para modificar una persona
    @PutMapping("/personas/editar/{id_original}")
    public Persona editaPersona(@PathVariable Long id_original,
                                @RequestParam(required = false,name="id")Long nuevaId,
                                @RequestParam(required = false,name="nombre")String nuevoNombre,
                                @RequestParam(required = false,name="apellido")String nuevoApellido,
                                @RequestParam(required = false,name="edad")int nuevaEdad){
        
        //Envio id original (para buscar)
        //Envio nuevos datos para modificar
        persoServ.editPersona(id_original, nuevaId, nuevoNombre, nuevoApellido, nuevaEdad);
        
        //buscamos la persona editada para mostrarla en el response
        Persona perso=persoServ.findPersona(id_original);
        
        //retorno la nueva persona
        return perso;      
    }
    
    //Otro metodo para editar persona
    @PutMapping("/personas/editart")
    public Persona editPerson(@RequestBody Persona per){
        persoServ.editPersona(per);
        
        return persoServ.findPersona(per.getId());
    }
}
