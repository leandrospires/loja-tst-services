package br.com.alura.loja.modelo;

import java.util.Iterator;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Projeto {

	private long Id;
	private String nome;
	private int anoDeInicio;

	public Projeto(long id, String nome, int anoDeInicio) {
		super();
		Id = id;
		this.nome = nome;
		this.anoDeInicio = anoDeInicio;
	}
	
	public Projeto () {
	}
	
	public void remove(long id) {
		
	}
	
	public String toXML() {
		
		return new XStream().toXML(this);
		
	}
	
	public String toJson() {
		return new Gson().toJson(this);
	}
	
	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getAnoDeInicio() {
		return anoDeInicio;
	}

	public void setAnoDeInicio(int anoDeInicio) {
		this.anoDeInicio = anoDeInicio;
	}

}
