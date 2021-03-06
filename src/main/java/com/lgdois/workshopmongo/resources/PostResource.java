package com.lgdois.workshopmongo.resources;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lgdois.workshopmongo.domain.Post;
import com.lgdois.workshopmongo.domain.User;
import com.lgdois.workshopmongo.dto.UserDTO;
import com.lgdois.workshopmongo.resources.util.URL;
import com.lgdois.workshopmongo.services.PostService;



@RestController
@RequestMapping(value="posts")
public class PostResource {
	
	@Autowired
	private PostService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)          
	public ResponseEntity<Post> findById(@PathVariable String id){
		Post obj = service.findById(id);
		
		return ResponseEntity.ok().body(obj);
	}
	//Esse metodo tb vai ser um get, o caminho vai ser "/titlesearch", a resposta do metodo abaixo vai ser uma lista de Post, o
	//nome do metodo vai ser findByTitle, o meu criterio de busca vai ser passado como um parametro, ie,  com uma "?" na frente e não como
	//uma variavel de URL , ie,  com uma "/" , entao vamos mudar a sintaxe, não vai ser mais um @PathVariable como no metodo
	//findById acima, neste caso será @RequestParam e para completar esse annotation, vamos colocar entre os parenteses value = "text", 
	//para o endpoint identificar o nome do parametro, e para finalizar eu vou colocar um defaultValue = "" para que se o parametro
	//nao for informado sera colocado uma string vazia.
	

	
	@RequestMapping(value="/titlesearch", method=RequestMethod.GET)          
	public ResponseEntity<List<Post>> findByTitle(@RequestParam(value = "text", defaultValue = "") String text){
		text = URL.decodeParam(text);
		List<Post> list = service.findByTitle(text);
		return ResponseEntity.ok().body(list);
	}
	
	@RequestMapping(value="/fullsearch", method=RequestMethod.GET)          
	public ResponseEntity<List<Post>> fullSearch(
			@RequestParam(value = "text", defaultValue = "") String text,
			@RequestParam(value = "minDate", defaultValue = "") String minDate,
			@RequestParam(value = "maxDate", defaultValue = "") String maxDate){
		text = URL.decodeParam(text);
		Date min = URL.convertDate(minDate, new Date(0L));//Caso de algum problema nessa conversão, eu vou gerar uma data minima do sistema, entrando com new Date(0L) passando o valor 0L como argumento, com isso ele vai gerar a data minima do tipo Date do java que é 01 01 1970.
		Date max = URL.convertDate(maxDate, new Date());  //neste caso igual ao de cima só que gerando o instante atual com "new Date()" .
		List<Post> list = service.fullSearch(text, min, max); 
		return ResponseEntity.ok().body(list);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
