package model;

import java.io.Serializable;

public class Administrador implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5592210858454794266L;
	int idAdministrador;
	String tipo;
	Pessoa pessoa;
	
	public Administrador(int idAdministrador, String tipo, Pessoa pessoa) {
		super();
		this.idAdministrador = idAdministrador;
		this.tipo = tipo;
		this.pessoa = pessoa;
	}

	public Administrador(String tipo, Pessoa pessoa) {
		this.tipo = tipo;
		this.pessoa = pessoa;
	}

	public int getIdAdministrador() {
		return idAdministrador;
	}

	public void setIdAdministrador(int idAdministrador) {
		this.idAdministrador = idAdministrador;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
	
	
}
