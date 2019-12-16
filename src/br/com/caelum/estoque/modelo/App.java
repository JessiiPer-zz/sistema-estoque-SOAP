package br.com.caelum.estoque.modelo;

import javax.xml.ws.Endpoint;

import br.com.caelum.estoque.modelo.estoque.ws.EstoqueWS;

public class App {

	public static void main(String[] args) {
		
		EstoqueWS service = new EstoqueWS();
		String url = "http://localhost:8080/estoquews";
		
		Endpoint.publish(url, service);
	}
}
