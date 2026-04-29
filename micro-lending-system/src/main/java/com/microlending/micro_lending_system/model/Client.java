package com.microlending.micro_lending_system.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Clients")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;

    @Column(unique = true,nullable = false)
    private String taxId;//RFC

    private String email;

    private Boolean active=true;

    public String getFullName(){
        return firstName+" "+lastName;
    }
}
