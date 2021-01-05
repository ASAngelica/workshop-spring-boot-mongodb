package com.lgdois.workshopmongo.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lgdois.workshopmongo.domain.User;
import com.lgdois.workshopmongo.services.UserService;

//Esta classe é um recurso Rest, portanto vamos colocar annotation " @RestController na classe"
//REST(Representational State Transfer, que significa Transferência Representacional de Estado) 
//é um modelo de arquitetura e não uma linguagem ou tecnologia de programação, que fornece diretrizes 
//para que os sistemas distribuídos se comuniquem diretamente usando os princípios e protocolos existentes da Web 
//sem a necessidade de SOAP ou outro protocolo sofisticado.
//A arquitetura REST é simples e fornece acesso aos recursos para que o cliente REST acesse e 
//renderize os recursos no lado do cliente. No estilo REST, URI ou IDs globais ajudam a identificar cada recurso.

//Está arquitettura usa várias representações de recursos para representar seu tipo, como XML, JSON, Texto, 
//Imagens e assim por diante.

//Vale ressaltar que o REST não se limita a solicitações e respostas de registros. 
//Também é possível inserir um novo registro ou deletar um já existente.

//Porque Resourses? Pq é o termo técnico para referenciar os nossos recursos Rest, são recursos que o nosso back-end vai usar
//do ponto de vista da implementação do back-end, também usamos o termo "Controlador" por isso são os controladores rest que vão
//disponibilizar os endpoints para nós, o termo controlador é do ponto de vista de implementação e independente disso o termo mais
//correto é "Resources".

@RestController
@RequestMapping(value="users") //por padrão colocamos o nome do recurso no plural "users"
public class UserResource {
	
	//Aqui na classe, refatorar o código, usando o UserService para buscar os usuários, mais uma vez nós vamos pensar nas camadas
	//o controladorREST ele vai conversar com serviço então aqui agora na classe UserResource da mesma forma que eu injetei o Repository 
	//lá no service, eu vou injetar o serviço aqui.
	//Dessa forma fica bem claro o papel das camadas, O CONTROLADOR REST(UserResource) ACESSA O SERVIÇO(UserService),
	//E O SERVIÇO POR SUA VEZ ACESSA O REPOSITORIO (UserRepository);
	
	@Autowired
	private UserService service;
	
	//Para que este método seja o endpoint rest deste caminho "/users", colocar a annotation @RequestMapping ...
	//No lugar do método retornar uma lista simplesmente, vamos usar um objeto sofisticado aqui do Spring que é o 
	//"ResponseEntity<T>" esse objeto vai encapsular toda uma estrutura necessaria para retornar respostas http 
	//já com possíveis cabeçalhos, possíveis erros etc...
	
	@RequestMapping(method=RequestMethod.GET) // pode ser usado tb "@GetMapping" ...
	public ResponseEntity<List<User>> findAll(){
		List<User> list = service.findAll();
		return ResponseEntity.ok(list); //para retornar o método vamos ter que instanciar o obj ResponseEntity.ok(budy), ok é o método
		// que vai instanciar o ResponseEntity já com o código de resposta http informando que a resposta ocorreu com sucesso e budy para 
		//definir qual vai ser o corpo da resposta, vamos colocar a palavrinha list como budy, ou seja no corpo da minha resposta vai ter 
		//esta lista que montamos no método.
	}

}
