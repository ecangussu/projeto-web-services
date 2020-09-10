package com.estevaohcsouza.projetowebservices.services.exceptions;

public class ResourceNotFoundException extends RuntimeException {

	//RuntimeException > compilador não obriga a tratar a exceção
	
	private static final long serialVersionUID = 1L;
	
	//Object id > id do objeto que tentou encontrar na requisição
	public ResourceNotFoundException(Object id) {
		super("Recurso não encontrado. Id: " + id);
	}

}
