package com.pruebaTecnica.repositorio;

import com.pruebaTecnica.entidad.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepositorio extends JpaRepository<Product, Integer> {

}
