package com.todocodeacademy.PruebaTecSupermercado.service;

import com.todocodeacademy.PruebaTecSupermercado.dto.SucursalDTO;
import com.todocodeacademy.PruebaTecSupermercado.exception.NotFoundException;
import com.todocodeacademy.PruebaTecSupermercado.mapper.Mapper;
import com.todocodeacademy.PruebaTecSupermercado.model.Sucursal;
import com.todocodeacademy.PruebaTecSupermercado.repository.ISucursalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SucursalService implements ISucursalService {

    @Autowired
    private ISucursalRepository repo;

    @Override
    public List<SucursalDTO> traerSucursales() {
        return repo.findAll().stream().map(Mapper::toDTO).toList();
    }

    @Override
    public SucursalDTO crearSucursal(SucursalDTO sucursalDTO) {
        Sucursal suc=Sucursal.builder()
                .nombre(sucursalDTO.getNombre())
                .direccion(sucursalDTO.getDireccion())
                .build();

        return Mapper.toDTO(repo.save(suc));
    }

    @Override
    public SucursalDTO actualizarSucursal(Long id, SucursalDTO sucursalDTO) {
        Sucursal suc=repo.findById(id).orElseThrow(()-> new NotFoundException("Sucursal no encontrada"));

        suc.setNombre(sucursalDTO.getNombre());
        suc.setDireccion(sucursalDTO.getDireccion());

        return Mapper.toDTO(repo.save(suc));
    }

    @Override
    public void eliminarSucursal(Long id) {
        if (!repo.existsById(id)) throw new NotFoundException("Sucursal no encontrada");

        repo.deleteById(id);
    }
}
