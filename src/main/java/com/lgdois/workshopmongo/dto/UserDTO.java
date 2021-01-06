package com.lgdois.workshopmongo.dto;

import java.io.Serializable;

import com.lgdois.workshopmongo.domain.User;

public class UserDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String name;
	private String email;
	
	public UserDTO() {
	}
	
	//Criamos este construtor sobrecarregado do UserDTO, recebendo como argumento um objeto do tipo User para termos
	//uma forma automatizada de instanciar um UserDTO a partir de um User. Usando o método get recuperamos os dados
	//do User ex: id = obj.getId();
	public UserDTO(User obj) {
		super();
		id = obj.getId();
		name = obj.getName();
		email = obj.getEmail();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
