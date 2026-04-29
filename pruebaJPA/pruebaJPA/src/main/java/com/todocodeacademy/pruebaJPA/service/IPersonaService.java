package com.todocodeacademy.pruebaJPA.service;

import com.todocodeacademy.pruebaJPA.model.Persona;
import java.util.List;

public interface IPersonaService {
    
    //metodo para traer todas las personas
    public List<Persona> getPersonas();
    
    //metodo para dar de alta una persona
    public void savePersona(Persona perso);
    
    //metodo para borrar una persona
    public void deletePersona(Long id);
    
    //metodo para encontrar una perosna
    public Persona findPersona(Long id);
    
    //metodo para editar una persona
    public void editPersona(Long idOriginal, Long idNueva,String nuevoNombre,String nuevoApellido,int nuevaEdad);

    public void editPersona(Persona per);
}
