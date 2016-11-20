package model;

public class Localizacao {
	
	int id, numero,complemento;
	String rua, cidade, estado, pais;
	public Localizacao(String rua, int numero, int complemento,  String cidade, String estado, String pais) {
		super();
		this.numero = numero;
		this.complemento = complemento;
		this.rua = rua;
		this.cidade = cidade;
		this.estado = estado;
		this.pais = pais;
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
	
	
	
}
