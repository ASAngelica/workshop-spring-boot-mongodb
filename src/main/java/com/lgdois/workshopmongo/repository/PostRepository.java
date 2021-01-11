package com.lgdois.workshopmongo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.lgdois.workshopmongo.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {
	
	//vamos fazer a mesma consulta que fizemos abaixo com o query methods, só que agora eu vou especificar a consulta do MondoDB manualmente
	//usando o string Json, vamos então fazer a implementação alternativa da consulta, neste caso agora eu dou o nome do metodo que eu quiser
	//já que esse método agora vai ser personalizado, vamos usar dessa vez o annotation @Query e entre parenteses, vamos colocar o meu Json,
	//e dentro dos parenteses vamos colocar a nossa consulta, na documentação, tem a REGEX (Expressão Regular) que vai usar,
	//( { <field>: { $regex: /pattern/, $options: '<options>' } })com o nome do campo:,
	//ai vai abrir um objeto regex, $regex:, a expressão, e as opções.
	
  //@Query({ <field>: { $regex: /pattern/, $options: '<options>' } }
	@Query("{ 'title': { $regex: ?0, $options: 'i' } }")// 'title' é o campo do MongoDB que vamos pesquisar, ?0 é o parametro do método que vamos passar no caso 'text' e como
	List<Post> searchTitle(String text); 
	// só tem um parametro acrescentamos o '0' na '?', ficando '?0", e 'i' é a opção para case insensitive.   
	
	
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
	
	
	
	/*Essa consulta abaixo vai ficar bem maior que a primeira, a primeira coisa que temos que entender, é que eu vou ter que
	  busar o texto ou no titulo, ou no corpo ou nos comentarios, então vou ter que usar o operador "$or" do MongoDB, que 
	  se encontra na documentação " https://docs.mongodb.com/manual/reference/operator/query/regex/", depois de fazer esse OR
	  eu também vou ter que testar a data minima (minDate) e data máxima (maxDate), neste caso o operador logico "$and" do
	  MongoDB, esse testo vai estar '$or' no titulo '$or' no corpo '$or' nos comentarios, e vai ter 
	  uma data minima '$and' uma data máxima, então eu vou ter um operador $and e dentro desse $and em uma das expressões dele,
	  vai ser uma expressão $or, para buscar o titulo nas três possibilidades:
	  
	   $and

       Syntax: { $and: [ { <expression1> }, { <expression2> } , ... , { <expressionN> } ] }
	  
	  
	  
	*/
	
	@Query("{ $and: [ {date: {$gte: ?1} }, { date: { $lte: ?2} } , { $or: [ { 'title': { $regex: ?0, $options: 'i' } }, { 'body': { $regex: ?0, $options: 'i' } }, { 'comments.text': { $regex: ?0, $options: 'i' } } ] } ] } ")
	List<Post> fullSearch(String text, Date minDate, Date maxDate);

}

//Como é a implementação do UserRepository usando o Spring Data? Duas coisas, primeiro a annotation @Repository
//Esta interface vai herdar da interface MongoRepository que já tem o Spring Data, este mongo repository precisa
//de dois dados "MongoRepository< 111, 222>, 111 é o tipo da classe de dominio que ele vai gerenciar, neste caso
//Post, 222 é o tipo de id desta  classe, no caso  é String.

// Só com isso um obj do tipo PostRepository vai ser capaz de fazer varias operações básicas com os posts
// salvar, recuperar, deletar, atualizar tudo isso já esta embutido aqui no MongoRepository.
