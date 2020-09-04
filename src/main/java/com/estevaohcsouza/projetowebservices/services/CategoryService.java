package com.estevaohcsouza.projetowebservices.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estevaohcsouza.projetowebservices.entities.Category;
import com.estevaohcsouza.projetowebservices.repositories.CategoryRepository;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	 
	public List<Category> findAll() {
		return categoryRepository.findAll();
	}
	
	//operação para buscar o usuário pelo id
	public Category findById(Long id) {
		Optional<Category> obj = categoryRepository.findById(id);
		return obj.get();
	}

}
