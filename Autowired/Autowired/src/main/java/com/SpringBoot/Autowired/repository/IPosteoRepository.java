package com.SpringBoot.Autowired.repository;

import com.SpringBoot.Autowired.model.Posteo;
import java.util.List;

public interface IPosteoRepository {
    public List<Posteo> traerTodos();
}
