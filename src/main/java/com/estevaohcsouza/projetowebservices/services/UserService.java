package com.estevaohcsouza.projetowebservices.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estevaohcsouza.projetowebservices.entities.User;
import com.estevaohcsouza.projetowebservices.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	//operação para buscar todos os usuários
	//operação (findAll) na camada de serviço que repassa a chamada para o userRepository findAll (por isso a injeção de dependência) 
	public List<User> findAll() {
		return userRepository.findAll();
	}
	
	//operação para buscar o usuário pelo id
	public User findById(Long id) {
		Optional<User> obj = userRepository.findById(id);
		//O findByAdd retorna um objeto do tipo Optional
		return obj.get();
		//o get retorna um objeto do tipo User que estiver dentro do optional (representado pelo obj)
	}

}
