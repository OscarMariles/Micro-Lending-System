package com.ejemplo.DTOprueba.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class aplicacionCotroller {
    
    @GetMapping("/propiedad/{id}")
    @ResponseBody
    public PropiedadDTO devolverPropiedad(@PathVariable Long id){
        //a traves del id buscamos la propiedad en cuestion
        //trajimos al inquilino asociado a esa propiedad
        
        Propiedad prop=new Propiedad(15487L,"Casa","308 Negro Arrollo Lane",200.0,40000.0);
        Inquilino inqui=new Inquilino(1L, "12345678", "Walter", "White", "Profesor de Quimica");
        
        PropiedadDTO propiDTO=new PropiedadDTO();
        
        //Asignamos datos que necesitamos de Propiedad
        propiDTO.setId_propiedad(prop.getId_propiedad());
        propiDTO.setTipo_propiedad(prop.getTipo_propiedad());
        propiDTO.setDireccion(prop.getDireccion());
        propiDTO.setValor_alquiler(prop.getValor_alquiler());
        //Asignamos datos que necesitamos de inquilinos
        propiDTO.setNombre(inqui.getNombre());
        propiDTO.setApellido(inqui.getApellido());
        
        return propiDTO;
    }
}
