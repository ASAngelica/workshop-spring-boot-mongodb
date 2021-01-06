package com.lgdois.workshopmongo.services.exception;

public class ObjectNotFoundException extends RuntimeException { //RuntimeException é uma exceção padrão que o compilador 
	private static final long serialVersionUID = 1L;            //n exige que a trate.
	/*
	 vamos sobrecarregar o construtor basico dessa classe que recebe um string como argumento
	 repassando a chamada para superclasse RuntimeException atraves do metodo super() repassando
	 o argumento(msg);
	 */
	public ObjectNotFoundException(String msg) {
		super(msg);
	}
}
