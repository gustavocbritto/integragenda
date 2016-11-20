package model;

public class Categoria {
	
	
	int id;
	String descricao;
	
	
	
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
