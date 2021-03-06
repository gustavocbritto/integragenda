package model;

import java.io.Serializable;

import persistence.LocalizacaoDAO;

public class Localizacao implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 6639738799988807845L;
	int id, numero,complemento;
	String rua, cidade, estado, pais;
	LocalizacaoDAO localizacaoDAO = new LocalizacaoDAO();
	public Localizacao(String rua, int numero, int complemento,  String cidade, String estado, String pais) {
		super();
		this.numero = numero;
		this.complemento = complemento;
		this.rua = rua;
		this.cidade = cidade;
		this.estado = estado;
		this.pais = pais;
	}
	public Localizacao() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public int getComplemento() {
		return complemento;
	}
	public void setComplemento(int complemento) {
		this.complemento = complemento;
	}
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public void salvar() throws Exception{
		if(id > 0)
			localizacaoDAO.salvar(this);
		else
			localizacaoDAO.inserir(this);
	}
	public boolean podeSalvar() {
		boolean retorno =  false;
		if(   	numero > 0 &&
				complemento > 0 &&
				!rua.equals("") &&
				!cidade.equals("") &&
				!estado.equals("") &&
				!pais.equals(""))
			retorno = true;
		return retorno;
	}
	
	
	
	
}
