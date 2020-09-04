package com.estevaohcsouza.projetowebservices.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estevaohcsouza.projetowebservices.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
