package model;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import persistence.AgendaDAO;
import persistence.PessoaDAO;

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
	private String nomeAtual = "Usuario";
	private Double preco;
	private ArrayList<Agenda> agendas;
	
	private AgendaDAO agendaDAO = new AgendaDAO();

	public Usuario(){
		this.pessoa = new Pessoa();
	}
	
	public void logout() throws Exception
	{
		nomeAtual = "Usuario";
		idUsuario = 0;
		pessoa = new Pessoa();
		preco = 0.0;
		agendas = new ArrayList<Agenda>();
		FacesContext.getCurrentInstance().getExternalContext().redirect("index.jsf");
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
	
	public void minhaAgenda() throws Exception
	{
		if(!nomeAtual.equals("Usuario"))
		{
			agendas = agendaDAO.getAgendaUsuario(pessoa.getId());
			FacesContext.getCurrentInstance().getExternalContext().redirect("minhaAgenda.jsf");
		}else
		{
			addMessage("ERRO:","Usuario deve estar logado!");
		}
	}
	
	public void minhasSalas() throws IOException
	{
		if(!nomeAtual.equals("Usuario"))
		{
			FacesContext.getCurrentInstance().getExternalContext().redirect("minhasSalas.jsf");
		}else
		{
			addMessage("ERRO:","Usuario deve estar logado!");
		}
		
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
		
		FacesContext context = FacesContext.getCurrentInstance();
		if(!cadastrar())
		{
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Senha confirmada incorretamente",""));
			return "usuario";
		}	
		
		return "mostraUsuario";
	}
	
	public boolean cadastrar() throws Exception
	{
		PessoaDAO pessoaDAO = new PessoaDAO();
		boolean retorno = false;
		if(this.pessoa.senha.equalsIgnoreCase(this.pessoa.confirmarsenha))
		{
			retorno =  true;
			pessoaDAO.gravar(this);
			
		}
		return retorno;
	}
	
    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
	public String verifica() throws Exception{
		
		
		boolean retorno = entrar();
		
		FacesContext context = FacesContext.getCurrentInstance();
		if (retorno){
			return "selecaoSala";
		}else{
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Usuario ou Senha não cadastrado",""));
			
		}
		
		return "login";
	}
	
	public boolean entrar() throws Exception
	{
		PessoaDAO pessoaDAO = new PessoaDAO();
		boolean retorno = pessoaDAO.verificaCadastro(this);
		if(retorno) { setNomeAtual(pessoa.getNome()); }
		return retorno;
	}
	
	public String update() throws Exception{
		
		PessoaDAO pessoaDAO = new PessoaDAO();
		
		
		pessoaDAO.update(this.getPessoa());
		
		
		return "meuPerfil";
		
		
	}
	
	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public ArrayList<Agenda> getAgendas() {
		return agendas;
	}

	public void setAgendas(ArrayList<Agenda> agendas) {
		this.agendas = agendas;
	}


	public void criarAgenda(Sala sala, Date horarioInicio, Date horarioFim, Date dt_inicial, Date dt_final) throws Exception{
        DateFormat dtOutput = new SimpleDateFormat("HH:mm");
        
		Agenda agenda = new Agenda();
		agenda.setDataInicio(new java.sql.Date(dt_inicial.getTime()));
		agenda.setDataFim(new java.sql.Date(dt_final.getTime()));
		agenda.setHoraInicio(dtOutput.format(horarioInicio));
		agenda.setHoraFim(dtOutput.format(horarioFim));
		agenda.setSala(sala);
		agenda.setStatus(false);
		agenda.salvar(pessoa.getId());
		this.agendas.add(agenda);
	}



}
