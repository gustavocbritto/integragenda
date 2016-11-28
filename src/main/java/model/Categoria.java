package model;

import java.io.Serializable;

public class Categoria implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -563484593639697665L;
	int id;
	String descricao;
	
	
	
	public Categoria(int id, String descricao) {
		super();
		this.id = id;
		this.descricao = descricao;
	}
	
	public Categoria(String descricao) {
		super();
		this.descricao = descricao;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
	
}
