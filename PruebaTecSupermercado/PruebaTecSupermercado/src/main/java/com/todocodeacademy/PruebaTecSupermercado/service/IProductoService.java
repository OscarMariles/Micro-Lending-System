package com.todocodeacademy.PruebaTecSupermercado.service;

import com.todocodeacademy.PruebaTecSupermercado.dto.ProductoDTO;
import com.todocodeacademy.PruebaTecSupermercado.model.Producto;

import java.util.List;

public interface IProductoService {

    List<ProductoDTO> traerProductos();
    ProductoDTO crearProducto(ProductoDTO productoDTO);
    ProductoDTO actializarProducto(Long id, ProductoDTO productoDTO);
    void eliminarProducto(Long id);
}
