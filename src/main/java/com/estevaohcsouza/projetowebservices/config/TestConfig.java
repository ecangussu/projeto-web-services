package com.estevaohcsouza.projetowebservices.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.estevaohcsouza.projetowebservices.entities.User;
import com.estevaohcsouza.projetowebservices.repositories.UserRepository;

@Configuration
//para informar ao spring que é uma classe específica de configuração
@Profile("test")
//configuração específica para o perfil test
//o nome entre "" deve ser identico ao fornecido no arquivo application.properties
//Spring consegue identificar que só irá rodar essa configuração quando estiver no perfil test
public class TestConfig implements CommandLineRunner {
	
	//classe de configuração para instanciar o BD
	
	@Autowired
	//Injeção de dependência
	private UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {
		//implementação da interface CommandLineRunner
		//tudo que for colocado dentro desse método será executado quando a aplicação for iniciada
		
		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");
		
		//salvando os usuários no bd
		//objeto repository que acessa os dados
		userRepository.saveAll(Arrays.asList(u1, u2));
	}	

}
