package com.pruebaTecnica.controlador;

import com.pruebaTecnica.entidad.Product;
import com.pruebaTecnica.servicio.ProductoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductoControlador {

    ProductoServicio productoServicio;

    @Autowired
    public ProductoControlador(ProductoServicio productoServicio) {
        this.productoServicio = productoServicio;
    }

    // guardar
    @PostMapping("/guardar")
    public ResponseEntity<String> guardar(@RequestBody Product product) {
        if(product.getNombre().isEmpty())
            return ResponseEntity.badRequest().body("Nombre del producto es requerido");
        else {
            this.productoServicio.guardarProducto(product);
            return ResponseEntity.ok("Producto guardado");
        }
    }

    // listar
    @GetMapping("/verProductos")
    public ResponseEntity<?> verListaProductos(){
        List<Product> listaProductos = this.productoServicio.verProductos();

        if(listaProductos.isEmpty()){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(listaProductos);
        }
    }

    // eliminar
    @DeleteMapping(path = "/eliminar/{id}")
    public ResponseEntity<String> eliminarProducto(@PathVariable Integer id ){
        boolean eliminado = productoServicio.eliminarIDProducto(id);
        if(eliminado){
            return ResponseEntity.ok("Producto con ID: "+ id + " Eliminado");
        }else{
            return ResponseEntity.ok("Producto con ID: "+ id +" no encontrado para eliminar!!");
        }

    }

    // actualizar
    @PutMapping(path = "/actualizar/{id}")
    public ResponseEntity<String> actualizarProducto(@RequestBody Product product, @PathVariable Integer id){
        String actualizacion= productoServicio.actualizarIDProducto(product, id);
        return ResponseEntity.ok(actualizacion);
    }


    @GetMapping("/hola")
    public String saludar(){
        return "Hola para la prueba tecnica";
    }
}
