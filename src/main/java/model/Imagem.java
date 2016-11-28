package model;

import java.io.Serializable;

import persistence.ImagemDAO;

public class Imagem implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8213513598404735672L;
	private int idImagem;
	private String url;
	private ImagemDAO imagemDAO = new ImagemDAO();
	public Imagem(int idImagem, String url) {
		super();
		this.idImagem = idImagem;
		this.url = url;
	}
	
	public Imagem(String url)
	{
		super();
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

	public void inserir() throws Exception{	
		imagemDAO.inserir(this);
	}
	
	
}
