package com.todocodeacademy.PruebaTecSupermercado.repository;

import com.todocodeacademy.PruebaTecSupermercado.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IVentaRepository extends JpaRepository<Venta,Long> {
}
