package com.lgdois.workshopmongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.lgdois.workshopmongo.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

}

//Como é a implementação do UserRepository usando o Spring Data? Duas coisas, primeiro a annotation @Repository
//Esta interface vai herdar da interface MongoRepository que já tem o Spring Data, este mongo repository precisa
//de dois dados "MongoRepository< 111, 222>, 111 é o tipo da classe de dominio que ele vai gerenciar, neste caso
//Post, 222 é o tipo de id desta  classe, no caso  é String.

// Só com isso um obj do tipo PostRepository vai ser capaz de fazer varias operações básicas com os posts
// salvar, recuperar, deletar, atualizar tudo isso já esta embutido aqui no MongoRepository.
