package br.com.alura.loja;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.xstream.XStream;

import br.com.alura.loja.modelo.Carrinho;
import br.com.alura.loja.modelo.Projeto;

public class ProjetoTeste {
	
	private HttpServer server;
	
	@Before
	public void startaServidor() {
		server = Servidor.inicializaServidor();
	}

	@After
	public void paraServidor() {
		server.stop();
	}
	
	@Test
	public void testaQueBuscarUmCarrinhoTrazOCarrinhoEsperado() {

		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8080");

		String conteudo = target.path("/projeto").request().get(String.class);

		System.out.println(conteudo);

		Projeto projeto = (Projeto) new XStream().fromXML(conteudo);
		System.out.println("Nome do Projeto: " + projeto.getNome());
		Assert.assertEquals("Minha loja", projeto.getNome());

	}

}
