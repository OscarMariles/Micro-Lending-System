package com.microlending.micro_lending_system.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientDTO {
    private Long id;

    @NotBlank(message = "El nombre completo es Obligatorio")
    @Size(min=2,max=100, message = "El nombre debe tener entre 2 y 100 caracteres")
    private String fullName;

    @NotBlank(message = "EL RFC es Obligatorio")
    @Pattern(
            regexp = "^[A-ZÑ&]{3,4}\\d{6}[A-Z0-9]{3}$",
            message = "El RFC no tiene un formato valido"
    )
    private String taxId;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email no tiene un formato válido")
    private String email;
}
