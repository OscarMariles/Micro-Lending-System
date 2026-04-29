package com.todocodeacademy.pruebaJPA.service;

import com.todocodeacademy.pruebaJPA.model.Persona;
import com.todocodeacademy.pruebaJPA.repository.IPersonaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonaService implements IPersonaService{
    
    @Autowired
    private IPersonaRepository persoRepo;

    @Override
    public List<Persona> getPersonas() {
        List<Persona> listaPersonas=persoRepo.findAll();
        return listaPersonas;
    }

    @Override
    public void savePersona(Persona perso) {
        persoRepo.save(perso);
    }

    @Override
    public void deletePersona(Long id) {
        persoRepo.deleteById(id);
    }

    @Override
    public Persona findPersona(Long id) {
        Persona perso=persoRepo.findById(id).orElse(null);
        return perso;
    }

    @Override
    public void editPersona(Long idOriginal, Long idNueva, String nuevoNombre, String nuevoApellido, int nuevaEdad) {
        
        //Ocupo this por que llamo al mismo metodo findPersona de esta clase
        Persona perso=this.findPersona(idOriginal);
        perso.setNombre(nuevoNombre);
        perso.setApellido(nuevoApellido);
        perso.setEdad(nuevaEdad);
        perso.setId(idNueva);
        
        //Ocupo this por que llamo al mismo metodo savePersona de esta clase
        this.savePersona(perso);
    }

    @Override
    public void editPersona(Persona per) {
        this.savePersona(per);
    }
}
