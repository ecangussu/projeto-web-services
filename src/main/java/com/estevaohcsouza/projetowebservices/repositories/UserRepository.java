package com.estevaohcsouza.projetowebservices.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estevaohcsouza.projetowebservices.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	//repository responsável por fazer operações com a entidade user
	//Spring DATA JPA já possui uma implementação padrão para essa interface, com isso não é necessário criar a implementação

}
