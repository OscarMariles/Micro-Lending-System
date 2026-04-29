package com.RepositoryandService.ArquitecturaMultiCapas.repository;

import com.RepositoryandService.ArquitecturaMultiCapas.model.Persona;

public interface IPersonaRepository {
   public void guardarPersons(Persona per);
   public Persona getPersona();
}
