package com.lgdois.workshopmongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.lgdois.workshopmongo.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {
	
	//Consulta simples com query methods
	//Referências:
		//https://docs.spring.io/spring-data/mongodb/docs/current/reference/html/
		//https://docs.spring.io/spring-data/data-document/docs/current/reference/html/
	
	//São metodos especiais que o spring data oferece para ele gerar automaticamente as consultas, eu coloquei acima duas referências
	//principais, a primeira é a referencia oficial do spring data para o MongoDB que contem os query methods com varias formas de vc
	//definir consultas escrevendo o nome do metodo para dessa forma o spring data gerar automaticamente a consulta.
	
	//Esse metodo vai retornar uma lista de Post e qual vai ser o nome do método? findBy obedecendo o padrão do spring, qual é o
	//atributo que queremos buscar? title, e na frente vamos colocar a palavra Containing e entao o metodo vai receber uma String text
	//como argumento, o IgnoraCase é para ignorar minuscula ou maiusculas.
	List<Post> findByTitleContainingIgnoreCase(String text);

}

//Como é a implementação do UserRepository usando o Spring Data? Duas coisas, primeiro a annotation @Repository
//Esta interface vai herdar da interface MongoRepository que já tem o Spring Data, este mongo repository precisa
//de dois dados "MongoRepository< 111, 222>, 111 é o tipo da classe de dominio que ele vai gerenciar, neste caso
//Post, 222 é o tipo de id desta  classe, no caso  é String.

// Só com isso um obj do tipo PostRepository vai ser capaz de fazer varias operações básicas com os posts
// salvar, recuperar, deletar, atualizar tudo isso já esta embutido aqui no MongoRepository.
