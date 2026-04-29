package com.RepositoryandService.ArquitecturaMultiCapas.service;

import com.RepositoryandService.ArquitecturaMultiCapas.model.Persona;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class PersonaService implements IPersonaService{

    @Override
    public void crearPersona(Persona per) {
        System.out.println("Persona Creada");
    }

    @Override
    public List<Persona> getPersonas() {
        //Aqio deberia devolver la lista de personas
        return null;
    }
    //metodos de logica de negocio.
}
