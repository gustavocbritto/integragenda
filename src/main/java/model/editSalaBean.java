package model;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.CloseEvent;

import persistence.SalaDAO;
import persistence.UtensilioDAO;

 
@ManagedBean(name="editSalaBean")
@SessionScoped
//ViewScoped
public class editSalaBean implements Serializable {


	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5290109662371105450L;
	private Sala sala;
    private List<Utensilio> utensilios;
    private Utensilio utensilioSelecionado;
    private List<String> paises, estados, cidades, nomeUtensilios;
    private String nomeU;

	//@ManagedProperty("#{utensilioService}")
   // private utensilioService service;
	
	@PostConstruct
    public void init() {
		//O id da sala que vou testar, vai ser a do ID 1. Depois tem que mudar pra pegar o ID dinamicamente
		//Que vira do clique da sala na tela Minhas Salas
		int idSala = 1;
		SalaDAO salaDAO = new SalaDAO();
		UtensilioDAO utensilioDAO = new UtensilioDAO();
		
    	try 
    	{
    		sala = salaDAO.consulta(idSala);
			//utensilios = service.getUtensilios();
    		utensilios = utensilioDAO.getLista();
    		nomeUtensilios = new ArrayList<String>();
    		addMessage("FOI: ", "");
    		for(Utensilio u : utensilios)
    		{
    			nomeUtensilios.add(u.getNome());
    		}
		} 
    	catch (Exception e) 
    	{
    		addMessage("ERROR: ", e.getMessage());
			System.out.println(e.getMessage());
		}
    }
	
	public List<String> getNomeUtensilios() {
		return nomeUtensilios;
	}


	public void setNomeUtensilios(List<String> nomeUtensilios) {
		this.nomeUtensilios = nomeUtensilios;
	}


	public String getNomeU() {
		return nomeU;
	}


	public void setNomeU(String nomeU) {
		this.nomeU = nomeU;
	}

 
    public Sala getSala() {
		return sala;
	}


	public void setSala(Sala sala) {
		this.sala = sala;
	}


	public List<Utensilio> getUtensilios() {
		return utensilios;
	}


	public void setUtensilios(List<Utensilio> utensilios) {
		this.utensilios = utensilios;
	}


	public Utensilio getUtensilioSelecionado() {
		return utensilioSelecionado;
	}


	public void setUtensilioSelecionado(Utensilio utensilioSelecionado) {
		this.utensilioSelecionado = utensilioSelecionado;
	}


	public List<String> getPaises() {
		return paises;
	}


	public void setPaises(List<String> paises) {
		this.paises = paises;
	}


	public List<String> getEstados() {
		return estados;
	}


	public void setEstados(List<String> estados) {
		this.estados = estados;
	}


	public List<String> getCidades() {
		return cidades;
	}


	public void setCidades(List<String> cidades) {
		this.cidades = cidades;
	}
     
   
    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    
}
