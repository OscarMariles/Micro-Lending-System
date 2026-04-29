package com.todocodeacademy.PruebaTecSupermercado.service;

import com.todocodeacademy.PruebaTecSupermercado.dto.ProductoDTO;
import com.todocodeacademy.PruebaTecSupermercado.exception.NotFoundException;
import com.todocodeacademy.PruebaTecSupermercado.mapper.Mapper;
import com.todocodeacademy.PruebaTecSupermercado.model.Producto;
import com.todocodeacademy.PruebaTecSupermercado.repository.IProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService implements IProductoService{

    @Autowired
    private IProductoRepository repo;

    @Override
    public List<ProductoDTO> traerProductos() {
        return repo.findAll().stream().map(Mapper::toDTO).toList();
    }

    @Override
    public ProductoDTO crearProducto(ProductoDTO productoDTO) {
        Producto prod= Producto.builder()
                .nombre(productoDTO.getNombre())
                .categoria(productoDTO.getCategoria())
                .precio(productoDTO.getPrecio())
                .cantidad(productoDTO.getCantidad())
                .build();

        return Mapper.toDTO(repo.save(prod));
    }

    @Override
    public ProductoDTO actializarProducto(Long id, ProductoDTO productoDTO) {

        //vamos a buscar si existe ese producto en la BD
        Producto prod=repo.findById(id).orElseThrow(()-> new NotFoundException("Producto no encontrado"));

        prod.setNombre(productoDTO.getNombre());
        prod.setCategoria(productoDTO.getCategoria());
        prod.setCantidad(productoDTO.getCantidad());
        prod.setPrecio(productoDTO.getPrecio());

        return Mapper.toDTO(repo.save(prod));
    }

    @Override
    public void eliminarProducto(Long id) {
        if (!repo.existsById(id)){throw new NotFoundException("Producto no encontrado para eliminar");}

        repo.deleteById(id);
    }
}
