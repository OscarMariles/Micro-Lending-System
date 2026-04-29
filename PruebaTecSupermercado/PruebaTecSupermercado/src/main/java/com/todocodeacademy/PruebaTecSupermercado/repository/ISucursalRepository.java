package com.todocodeacademy.PruebaTecSupermercado.repository;

import com.todocodeacademy.PruebaTecSupermercado.model.Sucursal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISucursalRepository extends JpaRepository<Sucursal,Long> {
}
