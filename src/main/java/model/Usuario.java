package model;

import java.io.IOException;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;


import persistence.PessoaDAO;
import persistence.SalaDAO;

@ManagedBean(name="usuario")
@SessionScoped
public class Usuario implements Serializable{




	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5545896148325336216L;
	
	private int idUsuario;
	private String tipo;
	private Pessoa pessoa;
	ControleSalasBean data;
	private String nomeAtual = "Usuario";

	private Double preco;
	
	

	public Usuario(){
		this.pessoa = new Pessoa();
	}
	
	public void login() throws IOException
	{
		FacesContext.getCurrentInstance().getExternalContext().redirect("Login.jsf");
	}
	
	public void cadastro() throws IOException
	{
		FacesContext.getCurrentInstance().getExternalContext().redirect("cadastro.jsf");
	}
	
	public void perfil() throws IOException
	{
		FacesContext.getCurrentInstance().getExternalContext().redirect("meuPerfil.jsf");
	}
	
	public void selecaoSala() throws IOException
	{
		FacesContext.getCurrentInstance().getExternalContext().redirect("selecaoSala.jsf");
	}
	
	public void minhaAgenda() throws IOException
	{
		FacesContext.getCurrentInstance().getExternalContext().redirect("minhaAgenda.jsf");
	}
	
	public void minhasSalas() throws IOException
	{
		FacesContext.getCurrentInstance().getExternalContext().redirect("minhasSalas.jsf");
	}
	public String getNomeAtual() {
		return nomeAtual;
	}

	public void setNomeAtual(String nomeAtual) {
		this.nomeAtual = nomeAtual;
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
		boolean retorno = pessoaDAO.verificaCadastro(this);
		setNomeAtual(pessoa.getNome());
		
		if (retorno){
			return "selecaoSala";
		}else{
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Usuario ou Senha não cadastrado",""));
			
		}
		
		return "login";
	}
	
	public String update() throws Exception{
		
		PessoaDAO pessoaDAO = new PessoaDAO();
		
		
		pessoaDAO.update(this.getPessoa());
		
		
		return "meuPerfil";
		
		
	}
	
	
	public ControleSalasBean getData() {
		return data;
	}

	public void setData(ControleSalasBean data) {
		this.data = data;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

}
