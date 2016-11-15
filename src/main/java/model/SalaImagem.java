package model;

public class SalaImagem {
	
	int idSalaImagem;
	Sala sala;
	Imagem Imagem;
	public SalaImagem(Sala sala, model.Imagem imagem) {
		super();
		this.sala = sala;
		Imagem = imagem;
	}
	public int getIdSalaImagem() {
		return idSalaImagem;
	}
	public void setIdSalaImagem(int idSalaImagem) {
		this.idSalaImagem = idSalaImagem;
	}
	public Sala getSala() {
		return sala;
	}
	public void setSala(Sala sala) {
		this.sala = sala;
	}
	public Imagem getImagem() {
		return Imagem;
	}
	public void setImagem(Imagem imagem) {
		Imagem = imagem;
	}
	
	
	
}
