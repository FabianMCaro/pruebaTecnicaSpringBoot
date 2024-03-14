package com.pruebaTecnica.servicio;

import com.pruebaTecnica.entidad.Product;
import com.pruebaTecnica.repositorio.ProductoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServicio {

    private final ProductoRepositorio productoRepositorio;

    @Autowired
    public ProductoServicio(ProductoRepositorio productoRepositorio) {
        this.productoRepositorio = productoRepositorio;
    }


    public void guardarProducto(Product product) {
        this.productoRepositorio.save(product);
    }

    public List<Product> verProductos(){
        return this.productoRepositorio.findAll();
    }

    public boolean eliminarIDProducto(Integer id){
        try {
            productoRepositorio.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public String actualizarIDProducto(Product product, Integer id){
        try{
            Product productoactualizar = productoRepositorio.findById(id).get();
            productoactualizar.setId(product.getId());
            productoactualizar.setNombre(product.getNombre());
            productoactualizar.setPrecio(product.getPrecio());
            productoRepositorio.save(productoactualizar);
            return "Producto con ID: "+ id +" actualizado";
        }catch (Exception e){
            return  "Producto con ID: "+ id +" no encontrado";
        }

    }

}
