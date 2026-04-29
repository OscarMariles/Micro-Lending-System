package com.todocodeacademy.PruebaTecSupermercado.service;

import com.todocodeacademy.PruebaTecSupermercado.dto.DetalleVentaDTO;
import com.todocodeacademy.PruebaTecSupermercado.dto.VentaDTO;
import com.todocodeacademy.PruebaTecSupermercado.exception.NotFoundException;
import com.todocodeacademy.PruebaTecSupermercado.mapper.Mapper;
import com.todocodeacademy.PruebaTecSupermercado.model.DetalleVenta;
import com.todocodeacademy.PruebaTecSupermercado.model.Producto;
import com.todocodeacademy.PruebaTecSupermercado.model.Sucursal;
import com.todocodeacademy.PruebaTecSupermercado.model.Venta;
import com.todocodeacademy.PruebaTecSupermercado.repository.IProductoRepository;
import com.todocodeacademy.PruebaTecSupermercado.repository.ISucursalRepository;
import com.todocodeacademy.PruebaTecSupermercado.repository.IVentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VentaService implements IVentaService{

    @Autowired
    private IVentaRepository ventaRepo;

    @Autowired
    private IProductoRepository productoRepo;

    @Autowired
    private ISucursalRepository sucursalRepo;


    @Override
    public List<VentaDTO> traerVentas() {
        List<Venta> ventas=ventaRepo.findAll();
        List<VentaDTO> ventasDTO=new ArrayList<>();
        VentaDTO dto;

        for (Venta v: ventas){
            dto = Mapper.toDTO(v);
            ventasDTO.add(dto);
        }

        return ventasDTO;
    }

    @Override
    public VentaDTO crearVenta(VentaDTO ventaDTO) {

        //validaciones
        if (ventaDTO==null) throw new NotFoundException("VentaDTO es null");
        if (ventaDTO.getIdSucursal()==null) throw new RuntimeException("Debe indicar la sucursal");
        if (ventaDTO.getDetalle()==null || ventaDTO.getDetalle().isEmpty()) throw new RuntimeException("Debe incluir al menos un producto");

        //Buscar la sucursal
        Sucursal suc=sucursalRepo.findById(ventaDTO.getIdSucursal()).orElse(null);
        if (suc==null) throw new NotFoundException("Sucursal no encontrada");

        //crear la venta

        Venta vent=new Venta();
        vent.setFecha(ventaDTO.getFecha());
        vent.setEstado(ventaDTO.getEstado());
        vent.setSucursal(suc);
        vent.setTotal(ventaDTO.getTotal());

        //Lista de detalles
        //--> Aqui estan los productos
        List<DetalleVenta> detalles=new ArrayList<>();

        Double totalCalculado=0.0;

        for (DetalleVentaDTO detDTO: ventaDTO.getDetalle()){
            //Buscar producto por id (tu detDTO usa id como id producto)
            Producto p=productoRepo.findByNombre(detDTO.getNombreProd()).orElse(null);
            if (p==null){ throw new NotFoundException("Producto no encontrado: "+detDTO.getNombreProd());}

            //Crear detalle
            DetalleVenta detalleVent=new DetalleVenta();
            detalleVent.setProd(p);
            detalleVent.setPrecio(detDTO.getPrecio());
            detalleVent.setCantProd(detDTO.getCantProd());
            detalleVent.setVenta(vent);

            detalles.add(detalleVent);
            totalCalculado=totalCalculado+(detDTO.getPrecio()*detDTO.getCantProd());
        }

        //Seteamos la lista de detalles venta
        vent.setDetalle(detalles);

        vent = ventaRepo.save(vent);

        VentaDTO ventaSalida=Mapper.toDTO(vent);
        //guardamos en la BD
        return ventaSalida;
    }

    @Override
    public VentaDTO actualizarVenta(Long id, VentaDTO ventaDTO) {
        //Buscar si venta existe
        Venta v=ventaRepo.findById(id).orElse(null);
        if(v==null)throw new RuntimeException("Venta no encontrada");

        if (ventaDTO.getFecha()!=null){v.setFecha(ventaDTO.getFecha());}
        if (ventaDTO.getEstado()!=null){v.setEstado(ventaDTO.getEstado());}
        if (ventaDTO.getTotal()!=null){v.setTotal(ventaDTO.getTotal());}
        if (ventaDTO.getIdSucursal()!=null){
            Sucursal suc=sucursalRepo.findById(ventaDTO.getIdSucursal()).orElse(null);
            if (suc==null)throw new NotFoundException("Sucursal no encontrada");
            v.setSucursal(suc);
        }

        return Mapper.toDTO(ventaRepo.save(v));
    }

    @Override
    public void eliminarVenta(Long id) {
        Venta v=ventaRepo.findById(id).orElse(null);
        if(v==null)throw new RuntimeException("Venta no encontrada");
        ventaRepo.delete(v);
    }
}
