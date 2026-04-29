package com.SpringBoot.Autowired.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Posteo {
    private Long id;
    private String titulo;
    private String autor;
}
