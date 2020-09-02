package com.estevaohcsouza.projetowebservices.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estevaohcsouza.projetowebservices.entities.User;

@RestController
//recurso Web que é implementado por um controlador rest
@RequestMapping(value = "/users")
//para dar um nome ao recurso 
public class UserResource {
	
	//essa classe irá disponibilizar um recurso Web correspondente a entidade User
	
	@GetMapping
	//getmapping: método que responde a requisição do tipo get do http
	//Método: endpoint para acessar users 
	//ResponseEntity: tipo específico do spring para retornar respostas de requisições web
	public ResponseEntity<User> findAll() {
		User u = new User(1L, "Maria", "maria@gmail.com", "16999999999", "123456");
		return ResponseEntity.ok().body(u);
		//ok: retorna resposta http como sucesso
		//body: retorna o corpo da resposta 
	}

}
