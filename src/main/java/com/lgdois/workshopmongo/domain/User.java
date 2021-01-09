package com.lgdois.workshopmongo.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

//Aqui no Spring para esta classe User corresponder a uma coleção lá do mongodb, que no nosso caso é a coleção "user",
//é só colocar aqui na classe de dominio a annotation @Document , vc pode opcionalmente abrir um parentese e colocar
//collection = "o nome da coleção do banco de dados" que no nosso caso é "user", se vc n colocar, o Spring Data vai
//mapear a coleção com o nome da classe só que tudo com letras minusculas então se vc colocar somente @Document tb
//funciona, além disso em cima do atributo que for a chave vc vai colocar a annotation @Id.

@Document(collection="user")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	private String name;
	private String email;
	
	//Criar o atributo "posts", usando o annotation @DBRef, aqui no spring data para vc falar que um atributo esta referenciando
	//outra coloeção do mongodb, basta você colocar o @DBRef e como boa prática incluir o atributo (lazy = true).
	
	//lazy = true -> como estamos referenciando uma coleção, por padrão, nós não queremos carregar automaticamente
    //os posts quando  recuperar um usuário do banco de dados, ex: vc esta recuperando os usuários para fazer
    //um relatório de usuários, a consulta retorna 1.000 usuários, se para cada usuário vier a lista de posts dele,
    //será muito dado e tráfego na rede desnecessário.
	@DBRef(lazy = true)
	private List<Post> posts = new ArrayList<>();// por boas práticas inciar a lista;
	
	public User() {
	}

	public User(String id, String name, String email) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
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
	
	public List<Post> getPosts() {
		return posts;
	}
	
	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
}
