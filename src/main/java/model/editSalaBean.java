package model;

import java.io.File;
import java.io.FileOutputStream;
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
import javax.servlet.ServletContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.CloseEvent;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import persistence.CategoriaDAO;
import persistence.SalaDAO;
import persistence.UtensilioDAO;

 
@ManagedBean(name="editSalaBean")
@SessionScoped
//ViewScoped
public class editSalaBean implements Serializable {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = -4058681982352100311L;
	private Sala sala;
    private List<Utensilio> utensilios;
    private List<Categoria> categorias;
	private Utensilio utensilioSelecionado;
    private List<String> paises, estados, cidades;
    private String nomeU, nomeC, nomeT;
    private Utensilio utensilioSelecionadoRemover;
    private String itemSelecionado;
    private List<String> listaItens;
    private String tamanhoInserido;
    private UploadedFile file;
    
	//@ManagedProperty("#{utensilioService}")
   // private utensilioService service;
    
    public UploadedFile getFile(){
        return file;
    }
 
    public void setFile(UploadedFile file){
        this.file = file;
    }
     
    public void upload(FileUploadEvent event) {
    	file = event.getFile();
    	try{
    		//file = event.getFile();
        if(file != null) {
        	FacesContext aFacesContext = FacesContext.getCurrentInstance();
        	ServletContext context = (ServletContext) aFacesContext.getExternalContext().getContext(); 
        	String realPath =  context.getRealPath("/resources/img/");
        	
        	byte[] arquivo = file.getContents();
        	String caminho = realPath + file.getFileName();
            FileOutputStream fos = new FileOutputStream(caminho);
            System.out.println(caminho);
            fos.write(arquivo);
            fos.close();
            addMessage("Succesful", file.getFileName() + " is uploaded.");
        }else
        {
        	 addMessage("TA NULL", "");
        }
    	}catch(Exception e)
    	{
    		addMessage(e.getMessage(), "");
    	}
    }

	public String getTamanhoInserido() {
		return tamanhoInserido;
	}

	public void setTamanhoInserido(String tamanhoInserido) {
		this.tamanhoInserido = tamanhoInserido;
	}

	@PostConstruct
    public void init() {
		//O id da sala que vou testar, vai ser a do ID 1. Depois tem que mudar pra pegar o ID dinamicamente
		//Que vira do clique da sala na tela Minhas Salas
		int idSala = 2;
		SalaDAO salaDAO = new SalaDAO();
		UtensilioDAO utensilioDAO = new UtensilioDAO();
		CategoriaDAO categoriaDAO = new CategoriaDAO();
		listaItens = new ArrayList<String>();
		listaItens.add("Categoria");
		listaItens.add("Utensilio");
		listaItens.add("Tamanho");
		try 
    	{
    		sala = salaDAO.consulta(idSala);
			//utensilios = service.getUtensilios();
    		utensilios = utensilioDAO.getLista();
    		categorias = categoriaDAO.getLista();
    		addMessage("FOI: ", "");

		} 
    	catch (Exception e) 
    	{
    		addMessage("ERROR: ", e.getMessage());
			System.out.println(e.getMessage());
		}
    }
	
    public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public String getNomeC() {
		return nomeC;
	}

	public void setNomeC(String nomeC) {
		this.nomeC = nomeC;
	}

	public String getNomeT() {
		return nomeT;
	}

	public void setNomeT(String nomeT) {
		this.nomeT = nomeT;
	}
	
	public String getItemSelecionado() {
		return itemSelecionado;
	}

	public void setItemSelecionado(String itemSelecionado) {
		this.itemSelecionado = itemSelecionado;
	}

	public List<String> getListaItens() {
		return listaItens;
	}

	public void setListaItens(List<String> listaItens) {
		this.listaItens = listaItens;
	}

	public Utensilio getUtensilioSelecionadoRemover() {
		return utensilioSelecionadoRemover;
	}

	public void setUtensilioSelecionadoRemover(Utensilio utensilioSelecionadoRemover) {
		this.utensilioSelecionadoRemover = utensilioSelecionadoRemover;
	}
	
	public void removerUtensilio()
	{
		if(utensilioSelecionadoRemover == null)
		{
			addMessage("Erro:", "Nao foi possivel remover!");
		}else
		{
			if(sala.removerUtensilio(utensilioSelecionadoRemover))
			{
				addMessage("Removeu:", utensilioSelecionadoRemover.getNome());
			}
		}
	}
	
	public void onItemChange()
	{		
		addMessage("MUDOU", "SIM");
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
