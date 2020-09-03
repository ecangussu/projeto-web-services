package com.estevaohcsouza.projetowebservices.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estevaohcsouza.projetowebservices.entities.User;
import com.estevaohcsouza.projetowebservices.services.UserService;

@RestController
//recurso Web que é implementado por um controlador rest
@RequestMapping(value = "/users")
//para dar um nome ao recurso 
public class UserResource {
	
	/*
	 * Essa classe irá disponibilizar um recurso Web correspondente a entidade User
	 * 
	 * Component Registration
	 * A classe que será injetada como dependência (UserService) tem de estar registrada como um componente do spring
	 * Ir até a classe UserService e adicionar a anotation @Component
	 * 
	 * Também pode-se usar a anotation @Service para registrar a classe como um serviço da camada de serviços 
	 * Pode usar qualquer um > usaremos o @Service para ficar mais semantico
	 * 
	 * A interface UserRepository também é injetada como dependência na classe UserService, porém não é necessário
	 * colocar a anotation @Repository porque ela está herdando do JpaRepository que já está registrado como componente do spring
	 * 
	*/
	
	@Autowired
	private UserService userService;
	
	@GetMapping
	//getmapping: método que responde a requisição do tipo get do http
	//Método: endpoint para acessar users 
	//ResponseEntity: tipo específico do spring para retornar respostas de requisições web
	public ResponseEntity<List<User>> findAll() {
		List<User> list = userService.findAll();
		return ResponseEntity.ok().body(list);
		//ok: retorna resposta http como sucesso
		//body: retorna o corpo da resposta 
	}
	
	@GetMapping(value = "/{id}")
	//Irá passar na url o valor do id do usuário 
	//Para informar que a url terá um parâmetro se usa o value = "/{}"
	//Requisição irá aceitar um id dentro da url
	public ResponseEntity<User> findById(@PathVariable Long id) {
		//PathVariable = utilizando o valor digitado na URL como parametro do metodo 
		User obj = userService.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	
}
