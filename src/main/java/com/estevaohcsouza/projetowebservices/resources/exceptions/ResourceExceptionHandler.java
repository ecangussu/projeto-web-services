package com.estevaohcsouza.projetowebservices.resources.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.estevaohcsouza.projetowebservices.services.exceptions.DatabaseException;
import com.estevaohcsouza.projetowebservices.services.exceptions.ResourceNotFoundException;

@ControllerAdvice
//vai interceptar as exceções que acontecerem para que este obj execute um possível tratamento
public class ResourceExceptionHandler {
	
	//tratamento manual para os erros
	
	@ExceptionHandler(ResourceNotFoundException.class)
	//quando interceptar a exceção cair aqui
	//este método irá interceptar qualquer exceção do tipo ResourceNotFoundException que for lançada e irá realizar o tratamento
	public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
		String error = "Recurso não encontrado";
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError standardError = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(standardError);
	}
	
	@ExceptionHandler(DatabaseException.class)
	public ResponseEntity<StandardError> database(DatabaseException e, HttpServletRequest request) {
		String error = "Erro na base de dados";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError standardError = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(standardError);
	}

}
