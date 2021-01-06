package com.lgdois.workshopmongo.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lgdois.workshopmongo.domain.User;
import com.lgdois.workshopmongo.dto.UserDTO;
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
	public ResponseEntity<List<UserDTO>> findAll(){
		List<User> list = service.findAll();// no meu serviço carrego uma lista de User;
		
		// Converter a lista de User para uma listDTO, 
        // declaramos uma lista de UserDTO e essa
        // list vai receber a conversão de cada elemento da lista original para listDTO. 
        // Essa converção se dará através de uma expressão lambda, vamos colocar list.stream() para
        // tramsformar em uma stream que é uma coleção compativel com as expressões lambda 
        // e  vamos chamar o método .map(), esse método vai pegar cada objeto .map(x ) na minha lista 
        // original , esse x pode ser um nome que vc quiser, e para cada objeto desse que vai ser 
        // um User, vamos retornar um new UserDTO passando esse x como argumento 
        // .map(x -> new UserDTO(x), para finalizar eu tenho que voltar este stream para uma lista
		// inserindo o .collect() e depois eu vou colocar o .collect(Collectors.tolist()) pronto
		// com uma linha apenas eu consigo converter cada objeto da minha lista original para um DTO.	
		List<UserDTO> listDto = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
			
	
		return ResponseEntity.ok().body(listDto);//para retornar o método vamos ter que instanciar o obj ResponseEntity.ok(budy), ok é o método
		// que vai instanciar o ResponseEntity já com o código de resposta http informando que a resposta ocorreu com sucesso e budy para 
		//definir qual vai ser o corpo da resposta, vamos colocar a palavrinha list como body, ou seja no corpo da minha resposta vai ter 
		//esta lista que montamos no método.
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)         //Para o argumento id passado pelo método findById casar com o id  
	public ResponseEntity<UserDTO> findById(@PathVariable String id){//passado na url(value="/{id}"), incluimos a annotation @PathVariable.
		User obj = service.findById(id);
		
		return ResponseEntity.ok().body(new UserDTO(obj));
	}
	
	@RequestMapping(method=RequestMethod.POST) // se preferir pode apagar @RequestMapping(ethod=RequestMethod.POST)  é só colocar a annotation @PostMapping
	public ResponseEntity<Void> insert(@RequestBody UserDTO objDto){ //esse endpoint vai receber como argumento um UserDTO, vou dar o nome objDto, para que esse endpoint aceit
		                                                             //esse objeto, eu tenho que colocar a annotation @RequestBody .
		User obj = service.fromDTO(objDto); // coverter esse DTO para User fazendo assim , service.fromDTO(objDto) recebendo um objeto DTO como argumento.
		                                    // com isso eu converti um obj DTO para User. Agora eu vou chamar o insert passando um obj como argumento.
		
		obj = service.insert(obj);
		
		// Inseriu no banco de dados, eu vou retornar uma resposta vazia porem nesta resposta, eu vou colocar um cabeçalho com a url do novo recurso criado
		// isso é uma boa pratica pra fazer isso eu vou copiar um codigo especifico do spring que faz exatamente isso.
		
		//Para fazer isso eu vou ter instanciar um objeto do tip uri e fazer essa chamada enorme a seguir passando o obj.getId() como argumento isso vai pegar
		//o end do novo objeto que eu inseri
		
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();// aqui eu vou chavar o ResponseEntity.created(), o método created() retorna o codigo 201 que é o código de resposta http quando
		                                           // se cria um novo recurso e ai eu vou passar esse caminho como argumento, esse comando vai me retornar uma resposta vazia com
		                                           // o codigo 201 e com o cabeçalho contendo a localização do novo recurso criado.
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
