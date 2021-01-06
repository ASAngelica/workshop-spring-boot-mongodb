package com.lgdois.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lgdois.workshopmongo.domain.User;
import com.lgdois.workshopmongo.repository.UserRepository;
import com.lgdois.workshopmongo.services.exception.ObjectNotFoundException;

// Em primeiro lugar para eu falar para o Spring que esta classe vai ser um serviço que possa ser injetável em
// outras classes, eu tenho que colocar a annotation @Service.
@Service
public class UserService {
	
	//como que eu vou implementar aqui na classe UserService uma operação para retornar todos os usuários do 
	//banco de dados? Seguindo o raciocinio das camadas o meu serviço tem que conversar com o repositorio , 
	//dentro da classe UserService eu vou instanciar um obj do tipo UserRepository, para instanciar automaticamente
	//um obj aqui, vamos usar a annotation @Autowired, logo abaixo vamos colocar private UserRepository e vou dar
	//o nome dessa variavel de repo, quando vc declara um obj dentro de uma classe usando a annotation @Autowired,
	//o proprio Spring vai procurar a definição deste obj que neste caso é o meu repositorio e vai instanciar o 
	//obj pra mim, n tem que fazer mais nada, ou seja é um mecanismo de Injeção de Dependencia automática do Spring.
	
	@Autowired
	private UserRepository repo;
	
	
	public List<User> findAll(){
		
		return repo.findAll(); //dentre o tanto de operações que já vem pronta no repository do Spring Data, tem inclusive
		                       //uma que chama findAll(), que é o metodo que vai ao banco de dados e retorna todos os obj 
		                       //do tipo User.
	}
	
	public User findById(String id) {
		Optional<User> obj = repo.findById(id);
	 	return obj.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado"));
		}
}
