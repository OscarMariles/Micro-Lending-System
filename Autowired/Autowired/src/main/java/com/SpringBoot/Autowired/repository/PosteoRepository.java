package com.SpringBoot.Autowired.repository;

import com.SpringBoot.Autowired.model.Posteo;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class PosteoRepository implements IPosteoRepository{

    @Override
    public List<Posteo> traerTodos() {
        List<Posteo> listaPosteos=new ArrayList<>();
        listaPosteos.add(new Posteo(1L,"Formater PC","Juan Camaney"));
        listaPosteos.add(new Posteo(2L,"Mantener sefuridad","Gordo Camaney"));
        
        return listaPosteos;
    }
    
}
