package com.SpringBoot.Autowired.controller;

import com.SpringBoot.Autowired.model.Posteo;
import com.SpringBoot.Autowired.repository.IPosteoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class aplicacionController {
    
    @Autowired
    IPosteoRepository repo;
    
    @GetMapping("/posteos")
    public List<Posteo> traerTodos(){
        return repo.traerTodos();
    }
}
