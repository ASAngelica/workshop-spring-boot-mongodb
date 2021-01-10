package com.lgdois.workshopmongo.resources.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class URL {
	
	//O método a seguir decodifica o parametro de URL da consulta cujo endpoint será mais ou menos assim,  vai ser um GET /posts,
	//vamos colocar assim titlesearch, que é o nome que vamos dar para o caminho, depois passamos o parâmetro obedecendo a sintaxe
	//do protocolo http que é o "?" depois o nome do parametro por ex "text=" e na frente do igual o valor que eu quero buscar,
	//por exemplo, se eu quiser buscar um texto dentro do titulo chamado "bom dia" por padrão eu não vou simplesmente escrever bom
	//dia, porque esse espaço em branco pode dar problema, sendo assim esse texto que vamos passar como parametro, vamos ter que
	//encodar (isso pode ser feito com a função js encodeURIComponent("bom dia") que retorna "bom%20dia") ele para que ele não fique 
	//com caracteres especiais.
	
	public static String decodeParam(String text) {
		try {
			return URLDecoder.decode(text, "UTF-8");
		}catch (UnsupportedEncodingException e){
			return "";
		}
	}
	


}
