package com.microlending.micro_lending_system.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InstallmentDTO {
    private Integer installmentNumber;
    private BigDecimal amount;
    private String dueDate; // Formato yyyy-MM-dd
    private Boolean isPaid;
}
