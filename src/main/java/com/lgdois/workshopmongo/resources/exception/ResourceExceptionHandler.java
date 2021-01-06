package com.lgdois.workshopmongo.resources.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.lgdois.workshopmongo.services.exception.ObjectNotFoundException;


//esta annotation do spring é para indicar que esta classe é responsável por tratar possíveis
//nas minhas requisições

@ControllerAdvice 
public class ResourceExceptionHandler {
	
	// Esse método vai ter que ser um ResponseEntity, o tipo de retorno acompanhado neste ResponseEntity vai ser o StandardError
	// que criamos, como parâmetro deste método eu vou ter que declarar duas coisas, o tipo da Exceção que vou tratar, ie, 
	// ObjectNotFoundException que criamos, e vou ter que declarar outro parâmetro aqui que é do tipo HttpServletRequest que é 
	// uma exigência do framework também, o que vai ter que ter dentro deste método, vai ter que ter um tratamento para Exceção
	// ObjectNotFoundException que criamos, ou seja quando estourar lá no meu serviço, esta Exceção, vou gerar um obj StandardError
	// e retornar esse objeto, vou declarar aqui um StandardError, vou chamar de err, recebendo um new StandardError com os arqgumentos
	// timestamp -> qual foi o estante que ocorreu este erro? vou chamar o System.currentTimeMillis(), qual vai ser o status de erro?
	// vou declarar um HttpStatus vou dar o nome da variável de status nela eu vou jogar o HttpStatus. -> no caso de uma Exceção de 
	// objeto não encontrado, fica assim HttpStatus.NOT_FOUND que vai reportar o erro 404, no status eu vou chamar status.value()
	// para ele converter para inteiro, qua vai ser o erro? vai ser "Não encontrato", e qual vai ser a menssagem do nosso erro?
	// a msg eu vou pegar aqui da exceção ObjectNotFoundException, vou chamar assim e.getMessage(), e qual voi o caminho(path) que 
	// gerou esta Exceção? ai vou usar o objto request passado como parâmetro pelo método para obter esse caminho, ie, 
	// request.getRequestURI().
	
	//Para funcionar, tenho que incluir a annotation do spring @ExceptionHandler() e colocar entre os parenteses a exceção que criamos.
	
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request){
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(System.currentTimeMillis(), status.value(), "Não encontrado", e.getMessage(), request.getRequestURI());
		
		// Agora eu vou ter que retornar este ResponseEntity , agora não vai ser o ResponseEntity.ok, vai ser o ResponseEntity.status para que  eu possa 
		// controlar manualmente qual o código de status que a minha requisição vai retornar eu eu vou colocar detro dos () o status que eu declarei
		// em HttpStatus, em seguida eu vou chamar o método .body() passando o meu err como argumento .body(err).
		return ResponseEntity.status(status).body(err);
	}

}
