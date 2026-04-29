package com.RepositoryandService.ArquitecturaMultiCapas.service;

import com.RepositoryandService.ArquitecturaMultiCapas.model.Persona;
import java.util.List;

public interface IPersonaService {
    public void crearPersona(Persona per);
    public List<Persona> getPersonas();
}
