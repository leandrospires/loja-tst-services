package br.com.alura.loja.resource;

import java.net.URI;
import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.thoughtworks.xstream.XStream;

import br.com.alura.loja.dao.CarrinhoDAO;
import br.com.alura.loja.modelo.Carrinho;
import br.com.alura.loja.modelo.Produto;

@Path("carrinhos")
public class CarrinhoResource {
	Date data = new Date();
	/*
	@Path("{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String busca(@PathParam("id") long id) {
		
		Carrinho carrinho = new CarrinhoDAO().busca(id);
				
		return carrinho.toXML();
	}
	*/
	
	
	@Path("/teste-post-param")
	@POST
	@Produces(MediaType.APPLICATION_XML)
	public Response testePostParam (String conteudo) {
		System.out.println(data.toString());
		System.out.println("Teste-Post Inicio");
		
		if (conteudo == null) {
			conteudo = "vazio";
			System.out.println(conteudo);
			return Response.ok().build();
		}
		
		System.out.println(conteudo);
		
		Carrinho carrinho = (Carrinho) new XStream().fromXML(conteudo);
		System.out.println("Conteudo (Rua): "  + carrinho.getRua());
		
		System.out.println("Teste-Post Chamado OK");
		return Response.ok().build();
	}


	@Path("/teste-post")
	@POST
	@Produces(MediaType.APPLICATION_XML)
	public Response testePost () {
		
		
		
		System.out.println(data.toString());
		System.out.println("Teste-Post Chamado: sem parâmetro");
		return Response.ok().build();
	}
	
	@Path("{id}")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Carrinho busca(@PathParam("id") long id) {
		
		Carrinho carrinho = new CarrinhoDAO().busca(id);
				
		return carrinho;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	public Response adiciona(String conteudo) {
		Carrinho carrinho = (Carrinho) new XStream().fromXML(conteudo);
		new CarrinhoDAO().adiciona(carrinho);
		
		URI uri = URI.create("/carrinhos/" + carrinho.getId());
		return Response.created(uri).build();
	}
	
	@Path("{id}/produto/{produtoId}")
	@DELETE
	public Response removeProduto(@PathParam("id") long id, @PathParam("produtoId") long produtoId) {
		Carrinho carrinho = new CarrinhoDAO().busca(id);
		carrinho.remove(produtoId);
		
		return Response.ok().build();
	}
	
	@Path("{id}/produto/{produtoId}/quantidade")
	@PUT
	public Response alteraProduto(String conteudo, @PathParam("id") long id, @PathParam("produtoId") long produtoId) {
		Carrinho carrinho = new CarrinhoDAO().busca(id);
		
		Produto produto = (Produto) new XStream().fromXML(conteudo);
		carrinho.trocaQuantidade(produto);
		
		return Response.ok().build();
		
		//PATCH - Atualiza um pedaço do recurso 
	}
}
