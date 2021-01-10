package com.lgdois.workshopmongo.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lgdois.workshopmongo.domain.Post;
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
}
