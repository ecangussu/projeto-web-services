package com.estevaohcsouza.projetowebservices.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.estevaohcsouza.projetowebservices.entities.User;
import com.estevaohcsouza.projetowebservices.services.UserService;

@RestController
@RequestMapping(value = "/users")
//para dar um nome ao recurso 
public class UserResource {
	
	/*
	 * Essa classe é um controlador rest e irá disponibilizar um recurso Web correspondente a entidade User
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
	//Requisição do tipo get: recuperar dados do BD
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
	
	@PostMapping
	//Requisição do tipo post: inserir dados no BD
	//Objeto obj irá chegar pelo modo JSON após feita a requisição > JSON será desserializado para um Objeto User do Java > Usar RequestBody
	//Mais adequado usar o código de resposta 201 > significa que criou um novo recurso
	public ResponseEntity<User> insert(@RequestBody User obj) {
		obj = userService.insert(obj);
		//Método created espera um objeto do tipo URI > no padrão http quando é retornado um 201 é esperado que a resposta contenha um cabeçalho (location) contendo o endereço do novo recurso inserido
		//Forma padrão do spring de gerar o endereço onde se encontra o novo recurso inserido
		//No caso o caminho será user/{id do novo recurso inserido} > por isso usar /{id}
		//O id do objeto inserido estará no obj (pois foi o novo recurso inserido) > obj.getId()
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
	@DeleteMapping(value = "/{id}")
	//void > a resposta dessa requisição não irá retornar nenhum "corpo" 
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		userService.delete(id);
		//noContent retorna uma resposta vazia
		//Código http de uma resposta sem conteúdo é o 204
		return ResponseEntity.noContent().build();
	}
	
}
