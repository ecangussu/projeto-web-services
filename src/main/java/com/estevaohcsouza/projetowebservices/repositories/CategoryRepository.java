package com.estevaohcsouza.projetowebservices.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estevaohcsouza.projetowebservices.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
