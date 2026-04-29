package com.todocodeacademy.PruebaTecSupermercado.mapper;

import com.todocodeacademy.PruebaTecSupermercado.dto.DetalleVentaDTO;
import com.todocodeacademy.PruebaTecSupermercado.dto.ProductoDTO;
import com.todocodeacademy.PruebaTecSupermercado.dto.SucursalDTO;
import com.todocodeacademy.PruebaTecSupermercado.dto.VentaDTO;
import com.todocodeacademy.PruebaTecSupermercado.model.Producto;
import com.todocodeacademy.PruebaTecSupermercado.model.Sucursal;
import com.todocodeacademy.PruebaTecSupermercado.model.Venta;

import java.util.stream.Collectors;

public class Mapper {

    //Mapero de Producto a productoDTO
    public static ProductoDTO toDTO(Producto p){
        if (p== null) return null;

        return ProductoDTO.builder()
                .id(p.getId())
                .nombre(p.getNombre())
                .categoria(p.getCategoria())
                .precio(p.getPrecio())
                .cantidad(p.getCantidad())
                .build();
    }

    //Mapeo de Venta a VentaDTO
    public static VentaDTO toDTO(Venta v){
        if (v==null) return null;

        var detalle= v.getDetalle().stream().map( det ->
                DetalleVentaDTO.builder()
                        .id(det.getProd().getId())
                        .nombreProd(det.getProd().getNombre())
                        .cantProd(det.getCantProd())
                        .precio(det.getPrecio())
                        .subtotal(det.getPrecio() * det.getCantProd())
                        .build()).collect(Collectors.toList());

        var total=detalle.stream()
                .map(DetalleVentaDTO::getSubtotal)
                .reduce(0.0,Double::sum);

        return VentaDTO.builder()
                .id(v.getId())
                .fecha(v.getFecha())
                .idSucursal(v.getSucursal().getId())
                .estado(v.getEstado())
                .detalle(detalle)
                .total(total)
                .build();
    }

    //Mapeo de Sucursal a SucursalDTO
    public static SucursalDTO toDTO(Sucursal s){
        if (s==null)return null;

        return SucursalDTO.builder()
                .id(s.getId())
                .nombre(s.getNombre())
                .direccion(s.getDireccion())
                .build();
    }
}
