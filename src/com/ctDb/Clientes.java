package com.ctDb;

public class Clientes{
	
	private String id;
	private String nome;
	private String contacto;
	private String morada;
	
	public Clientes(String ID, String Nome, String Contacto, String Morada)
	{
		setId(ID);
		setNome(Nome);
		setContacto(Contacto);
		setMorada(Morada);
	}
	
	public Clientes(Clientes newClient)
	{
		setId(newClient.getId());
		setNome(newClient.getNome());
		setContacto(newClient.getContacto());
		setMorada(newClient.getMorada());
	}
	
	public void setId(String id) {
		this.id = id;
	}
	public String getId() {
		return id;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getNome() {
		return nome;
	}
	public void setContacto(String contacto) {
		this.contacto = contacto;
	}
	public String getContacto() {
		return contacto;
	}
	public void setMorada(String morada) {
		this.morada = morada;
	}
	public String getMorada() {
		return morada;
	}
}