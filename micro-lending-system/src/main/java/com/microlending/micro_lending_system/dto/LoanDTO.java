package com.microlending.micro_lending_system.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoanDTO {
    private Long id;

    @NotNull(message = "El ID del cliente es Obligatorio")
    private Long clientId;

    @NotNull(message = "El monto principal es obligatorio")
    @DecimalMin(value = "500.00", message = "El monto mínimo de préstamo es $500")
    @DecimalMax(value = "500000.00", message = "El monto máximo de préstamo es $500,000")
    private BigDecimal principalAmount;

    @NotNull(message = "La tasa de interés es obligatoria")
    @DecimalMin(value = "0.1", message = "La tasa de interés mínima es 0.1%")
    @DecimalMax(value = "100.0", message = "La tasa de interés máxima es 100%")
    private Double interestRate;

    @NotNull(message = "El plazo en meses es obligatorio")
    @Min(value = 1, message = "El plazo mínimo es 1 mes")
    @Max(value = 120, message = "El plazo máximo es 120 meses (10 años)")
    private Integer termInMonths;
    /*
    usar String en los DTOs para mantener el contrato de la API independiente de mis tipos internos.
    Esto evita errores de acoplamiento y me permite validar o transformar los estados antes de que lleguen a la capa de persistencia.
     */
    private String status;
    private List<InstallmentDTO> installments;
}
