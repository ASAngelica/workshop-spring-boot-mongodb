package com.lgdois.workshopmongo.resources.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

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
	
	//Vamos gerar um método para converter a data para usar nas consultas no repositorio, neste método vamos receber uma data
	//na forma de string e uma data padrão caso a conversão falhe, "Date defaultValue" se ocorrer a falha, eu vou usar esse valor
	public static Date convertDate(String textData, Date defaultValue) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");// yyyy-MM-dd é o formato da data mais comum nosframeworks  para gerar uma data para nós.
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));//Estamos usando este padrao GMT, mais há quem prefira pegar o padrão da maquina do usuario, então será exigido que envie na requisição.
		try {
			return sdf.parse(textData);
		} catch (ParseException e) {
			return defaultValue;
		}
	}
	


}
