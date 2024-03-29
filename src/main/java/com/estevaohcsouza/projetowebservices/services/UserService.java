package com.estevaohcsouza.projetowebservices.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.estevaohcsouza.projetowebservices.entities.User;
import com.estevaohcsouza.projetowebservices.repositories.UserRepository;
import com.estevaohcsouza.projetowebservices.services.exceptions.DatabaseException;
import com.estevaohcsouza.projetowebservices.services.exceptions.ResourceNotFoundException;

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
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
		//orElseThrow = irá tentar dar o get, se não encontrar um usuário irá lançar uma exceção
		//return obj.get(); > método usado antes da inserção da exception
		//o get retorna um objeto do tipo User que estiver dentro do optional (representado pelo obj)
	}
	
	//operação para salvar um novo usuário no BD
	//operação irá retornar o usuário salvo
	//método save(obj) retorna o próprio objeto
	public User insert(User obj) {
		return userRepository.save(obj);
	}
	
	//operação para remover um usuário no BD
	public void delete(Long id) {
		try {
			userRepository.deleteById(id);			
		} 
		catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch(DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	//operação para atualizar os dados de um usuário no BD
	//irá retornar um usuário atualizado
	public User update(Long id, User obj) {
		try {
			//entity > objeto monitorado pelo JPA
			//getOne > instancia um usuário sem acessar o BD > prepara o objeto monitorado para serem realizadas as alterações para depois efetuar operações com o BD
			//Obs.: nas novas versões do spring não se usa mais o getOne(id) e sim o getReferenceById(id)
			User entity = userRepository.getOne(id);
			//Atualizar os dados da entidade baseado nos dados que chegaram no objeto
			updateData(entity, obj);
			//Salvar no BD o entity
			return userRepository.save(entity);			
		}
		catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(User entity, User obj) {
		entity.setName(obj.getName());
		entity.setEmail(obj.getEmail());
		entity.setPhone(obj.getPhone());
	}	

}
