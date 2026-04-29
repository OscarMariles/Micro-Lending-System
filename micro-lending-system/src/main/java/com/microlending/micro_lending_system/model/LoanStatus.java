package com.microlending.micro_lending_system.model;

public enum LoanStatus {
    PENDING_APPROVAL, // El cliente solicitó el préstamo, pero aún no se autoriza.
    APPROVED,         // El préstamo fue autorizado pero el dinero aún no se entrega.
    ACTIVE,           // El dinero fue entregado y el cliente está pagando a tiempo.
    OVERDUE,          // ¡Alerta! El cliente tiene al menos una cuota vencida (Moroso).
    PAID,             // El préstamo se liquidó exitosamente.
    REJECTED,         // No se autorizó el crédito tras el análisis de riesgo.
    CANCELLED         // Borrado lógico: el registro se "anuló" por error humano o cancelación.
}

