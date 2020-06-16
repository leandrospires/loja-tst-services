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

public class ClienteTeste {

	private HttpServer server;

	/*
	 * @Test public void testaQueaConexaoComOServidorFunciona() {
	 * 
	 * Client client = ClientBuilder.newClient(); WebTarget target =
	 * client.target("http://www.mocky.io");
	 * 
	 * String conteudo =
	 * target.path("/v2/52aaf5deee7ba8c70329fb7d").request().get(String.class);
	 * 
	 * System.out.println(conteudo);
	 * Assert.assertTrue(conteudo.contains("<rua>Rua Vergueiro 3185"));
	 * 
	 * }
	 */

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

		String conteudo = target.path("/carrinhos").request().get(String.class);

		System.out.println(conteudo);

		Carrinho carrinho = (Carrinho) new XStream().fromXML(conteudo);
		System.out.println("Rua: " + carrinho.getRua());
		Assert.assertEquals("Rua Vergueiro 3185, 8 andar", carrinho.getRua());

	}

}
