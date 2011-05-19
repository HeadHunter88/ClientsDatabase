package com.ctDb;

public class Clientes{
	
	private String id;
	private String nome;
	private String contacto;
	
	public Clientes(String ID, String Nome, String Contacto)
	{
		setId(ID);
		setNome(Nome);
		setContacto(Contacto);
	}
	
	public Clientes(Clientes newClient)
	{
		setId(newClient.getId());
		setNome(newClient.getNome());
		setContacto(newClient.getContacto());
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
}