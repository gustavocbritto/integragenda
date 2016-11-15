package model;

public class Imagem {
	
	int idImagem;
	String url;
	public Imagem(int idImagem, String url) {
		super();
		this.idImagem = idImagem;
		this.url = url;
	}
	public int getIdImagem() {
		return idImagem;
	}
	public void setIdImagem(int idImagem) {
		this.idImagem = idImagem;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
}
