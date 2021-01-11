package com.lgdois.workshopmongo.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lgdois.workshopmongo.domain.Post;
import com.lgdois.workshopmongo.repository.PostRepository;
import com.lgdois.workshopmongo.services.exception.ObjectNotFoundException;


@Service
public class PostService {
	
	@Autowired
	private PostRepository repo;
	
	public Post findById(String id) {
		Optional<Post> obj = repo.findById(id);
	 	return obj.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado"));
		}
	
	
	public List<Post> findByTitle(String text){
		return repo.searchTitle(text);
	}
	
	//Temos que acrescentar um dia na maxDate pq esta data maxima vai ser gerada no dia informado pelo usuário só que a meia noite, na verdade se eu quero
	//que a minha busca traga os posts até esta data, não pode ser até a meia noite daquele dia, tem que ser até 24hs daquele dia, porque a nossa data
	//é armazenada na forma de milisegundos, ela é um estante, ela não é simplesmente uma data, dia, mês e ano, ela tem hora, segundo e milisegundo, entao
	//se eu quero encontrar um post até uma certa data, eu tenho que considerar até o final daquele dia, até as 24hs daquele dia, como eu estou fazendo uma
	//comparação de menor ou igual, para achar a data maxima, então eu terei que fazer a comparação de menor ou igual da meia noite do proximo dia, veja
	//como vamos fazer: Vou chamar um new Date pegando o estante desta data inicial, pra isso eu vou chamar .getTime() e vou somar 24 horas na forma de 
	//milisegundos 24 * 60 *60 *1000.
	public List<Post> fullSearch(String text, Date minDate, Date maxDate){
		maxDate = new Date(maxDate.getTime()+ 24 * 60 * 60 * 1000);
		return repo.fullSearch(text, minDate, maxDate);
	}
	
	
	
}
