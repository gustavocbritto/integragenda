package model;

public class Utensilio {
	
	int idUtensilio;
	String nome;
	int quantidade;
	
	public Utensilio(int idUtensilio, String nome, int quantidade) {
		super();
		this.idUtensilio = idUtensilio;
		this.nome = nome;
		this.quantidade = quantidade;
	}
	
	public Utensilio(int idUtensilio, String nome) {
		super();
		this.idUtensilio = idUtensilio;
		this.nome = nome;
	}
	
	public int getIdUtensilio() {
		return idUtensilio;
	}
	public void setIdUtensilio(int idUtensilio) {
		this.idUtensilio = idUtensilio;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
}
