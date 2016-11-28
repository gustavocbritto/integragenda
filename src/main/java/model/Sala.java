package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Sala implements Serializable{


	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4691961094499054539L;
	
	int idSala;
	Categoria categoria;
	ArrayList<Utensilio> utensilios;
	ArrayList<Imagem> imagens;
	int tamanhoMin, tamanhoMax;
	Double preco;
	Localizacao localizacao;
	String descricao;
	Administrador administrador;
	Pessoa pessoa;
	int estrela;
	Boolean status;
	int numeroSala;

	
	public Sala(Categoria categoria, int tamanhoMin, int tamanhoMax, Double preco, Localizacao localizacao, String descricao, Administrador administrador, int estrela,
			Boolean status) {
		super();
		this.categoria = categoria;
		this.tamanhoMin = tamanhoMin;
		this.tamanhoMax = tamanhoMax;
		this.preco = preco;
		this.localizacao = localizacao;
		this.descricao = descricao;
		this.administrador = administrador;
		this.estrela = estrela;
		this.status = status;
	}
	
	public Sala(int id, Categoria categoria, int tamanhoMin, int tamanhoMax, Double preco, Localizacao localizacao, String descricao, Administrador administrador, int estrela,
			Boolean status) {
		super();
		this.idSala = id;
		this.categoria = categoria;
		this.tamanhoMin = tamanhoMin;
		this.tamanhoMax = tamanhoMax;
		this.preco = preco;
		this.localizacao = localizacao;
		this.descricao = descricao;
		this.administrador = administrador;
		this.estrela = estrela;
		this.status = status;
	}

	public Sala(Localizacao localizacao, int numeroSala){
		super();
		this.localizacao = localizacao;
		this.numeroSala = numeroSala;
	}
	
	public boolean removerUtensilio(Utensilio utensilio)
	{
		boolean retorno = false;
		for(Utensilio u : utensilios)
		{
			if(u.getIdUtensilio() == utensilio.getIdUtensilio())
			{
				retorno = true;
			}
		}
		if(retorno) {utensilios.remove(utensilio);}
		return retorno;
	}
	public int getNumeroSala()
	{
		return this.numeroSala;
	}
	
	public void setNumeroSala(int numeroSala)
	{
		this.numeroSala = numeroSala;
	}
	public int getIdSala() {
		return idSala;
	}

	public void setIdSala(int idSala) {
		this.idSala = idSala;
	}

	public String getImagemPrincipal()
	{
		String caminho = "";
		for(Imagem i : imagens)
		{
			caminho = i.getUrl();
			break;
		}
		return caminho;
	}

	public ArrayList<Imagem> getImagens() {
		return imagens;
	}

	public void setImagens(ArrayList<Imagem> imagens) {
		this.imagens = imagens;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public ArrayList<Utensilio> getUtensilios() {
		return utensilios;
	}

	public void setUtensilios(ArrayList<Utensilio> utensilios) {
		this.utensilios = utensilios;
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

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public Localizacao getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(Localizacao localizacao) {
		this.localizacao = localizacao;
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
