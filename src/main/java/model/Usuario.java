package model;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import persistence.PessoaDAO;
import persistence.SalaDAO;

@ManagedBean(name="usuario")
@RequestScoped
public class Usuario implements Serializable{



	private static final long serialVersionUID = -7643404588198283069L;
	
	public int idUsuario;
	public String tipo;
	public Pessoa pessoa;
	DataGridViewImagem data;
	

	private Double preco;
	
	

	public Usuario(){
		this.pessoa = new Pessoa();
	}
	
	public int getIdUsuario() {
		return idUsuario;
	}


	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
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
	
	public String salvar() throws Exception
	{
		PessoaDAO pessoaDAO = new PessoaDAO();
		
		FacesContext context = FacesContext.getCurrentInstance();
		if(! this.pessoa.senha.equalsIgnoreCase(this.pessoa.confirmarsenha))
		{
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Senha confirmada incorretamente",""));
			return "usuario";
		}
		
		pessoaDAO.gravar(this);
		
		
		return "mostraUsuario";
	}
	
	public String verifica() throws Exception{
		
		PessoaDAO pessoaDAO = new PessoaDAO();
		FacesContext context = FacesContext.getCurrentInstance();
		String retorno = pessoaDAO.verificaCadastro(this);
		
		
		if (retorno.equals("selecaoSala")){
			
			SalaDAO salaDAO = new SalaDAO();
			data = new DataGridViewImagem();
			data.setSalas(salaDAO.consultar());
			
			data.init();
				
			return retorno;
		}else{
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Usuario ou Senha não cadastrado",""));
			
		}
		
		return retorno;
	}

	public DataGridViewImagem getData() {
		return data;
	}

	public void setData(DataGridViewImagem data) {
		this.data = data;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}


}
