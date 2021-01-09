package com.lgdois.workshopmongo.dto;

import java.io.Serializable;
import java.util.Date;

//Essa classe é para acrescentar comentarios aos posts, no design do modelo de domínio nós decidimos colocar os comentários aninhados
//dentro de Post, então vamos ter o objeto Post e vamos acrescentar o atributo comments do tipo List com uma lista de comentarios,
//neste caso específico estamos considerando que os comentarios são dados muito simples que podem estar dentro do Post e não vamos 
// precisar identificar um comentario publicamente sendo assim optamos por implementar um comentario não como uma entidade, mas
//simplemente como um DTO.

public class CommentDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String text;
	private Date date;
	private AuthorDTO author;
	
	public CommentDTO() {
	}

	public CommentDTO(String text, Date date, AuthorDTO author) {
		super();
		this.text = text;
		this.date = date;
		this.author = author;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public AuthorDTO getAuthor() {
		return author;
	}

	public void setAuthor(AuthorDTO author) {
		this.author = author;
	}
}
