package model;


public class Sala {

	int idSala;
	String categoria;
	SalaUtensilio salaUtensilio;
	int tamanhoMin, tamanhoMax;
	SalaImagem salaImagem;
	Double pre�o;
	String localiza��o;
	String descricao;
	Administrador administrador;
	Pessoa pessoa;
	int estrela;
	Boolean status;


	public Sala(String categoria, SalaUtensilio salaUtensilio,
			int tamanhoMin, int tamanhoMax, SalaImagem salaImagem,
			Double pre�o, String localiza��o, String descricao,
			Administrador administrador, Pessoa pessoa, int estrela,
			Boolean status) {
		super();
		this.categoria = categoria;
		this.salaUtensilio = salaUtensilio;
		this.tamanhoMin = tamanhoMin;
		this.tamanhoMax = tamanhoMax;
		this.salaImagem = salaImagem;
		this.pre�o = pre�o;
		this.localiza��o = localiza��o;
		this.descricao = descricao;
		this.administrador = administrador;
		this.pessoa = pessoa;
		this.estrela = estrela;
		this.status = status;
	}

	public int getIdSala() {
		return idSala;
	}

	public void setIdSala(int idSala) {
		this.idSala = idSala;
	}
	
	public SalaImagem getSalaImagem() {
		return salaImagem;
	}

	public void setSalaImagem(SalaImagem salaImagem) {
		this.salaImagem = salaImagem;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public SalaUtensilio getSalaUtensilio() {
		return salaUtensilio;
	}

	public void setSalaUtensilio(SalaUtensilio salaUtensilio) {
		this.salaUtensilio = salaUtensilio;
	}

	public int getTamanhoMin() {
		return tamanhoMin;
	}

	public void setTamanhoMin(int tamanhoMin) {
		this.tamanhoMin = tamanhoMin;
	}

	public int getTamanhoMax() {
		return tamanhoMax;
	}

	public void setTamanhoMax(int tamanhoMax) {
		this.tamanhoMax = tamanhoMax;
	}

	public Double getPre�o() {
		return pre�o;
	}

	public void setPre�o(Double pre�o) {
		this.pre�o = pre�o;
	}

	public String getLocaliza��o() {
		return localiza��o;
	}

	public void setLocaliza��o(String localiza��o) {
		this.localiza��o = localiza��o;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Administrador getAdministrador() {
		return administrador;
	}

	public void setAdministrador(Administrador administrador) {
		this.administrador = administrador;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public int getEstrela() {
		return estrela;
	}

	public void setEstrela(int estrela) {
		this.estrela = estrela;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

}
