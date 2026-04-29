package com.RepositoryandService.ArquitecturaMultiCapas.repository;

import com.RepositoryandService.ArquitecturaMultiCapas.model.Persona;
import org.springframework.stereotype.Repository;

@Repository
public class PersonaRepository implements IPersonaRepository{

    @Override
    public void guardarPersons(Persona per) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Persona getPersona() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    //metodos para llamar a la base de datos.
}
