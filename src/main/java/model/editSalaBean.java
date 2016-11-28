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
import javax.servlet.http.HttpSession;

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
	private static final long serialVersionUID = 5909145939096459216L;
	private Sala sala;
    private List<Utensilio> utensilios;
    private List<Categoria> categorias;
	private Utensilio utensilioSelecionado;
    private List<String> paises, estados, cidades;
    private String nomeU, nomeC, nomeT;
    private Categoria categoriaSelecionada;
	private Utensilio utensilioSelecionadoRemover;
    private String itemSelecionado;
    private List<String> listaItens;
    private String tamanhoInserido;
    private UploadedFile file;
      
    @ManagedProperty(value = "#{usuario}")
    private Usuario usuario; 
	
    
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
    		utensilios = utensilioDAO.getLista();
    		categorias = categoriaDAO.getLista();
		} 
    	catch (Exception e) 
    	{
    		addMessage("ERROR:", e.getMessage());
			System.out.println(e.getMessage());
		}
    }
    
    public void upload(FileUploadEvent event) {
    	file = event.getFile();
    	try{
    		//file = event.getFile();
        if(file != null) {
        	FacesContext aFacesContext = FacesContext.getCurrentInstance();
        	ServletContext context = (ServletContext) aFacesContext.getExternalContext().getContext(); 

        	String realPath =  context.getRealPath("/resources/img/");
        	//System.out.println(context.getContextPath());
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
    
    public void salvarSala()
    {
    	try
    	{
			sala.salvar();
			addMessage("SUCESSO:", "Sala salva com sucesso!"+usuario.getPessoa().getNome());
    	}
    	catch(Exception e)
    	{
    		System.out.println(e.getMessage());
    		addMessage("ERRO:", "Nao foi possivel salvar a sala!");
    	}
    	
    }
    public void salvarInventario() // se quisermos podemos usar cadeia de responsabilidade aqui. Se tiver tempo adaptar
    {
    	try
    	{
	    	if (itemSelecionado.equals("Utensilio"))
	    	{
	    		for(Utensilio u : utensilios)
	    		{
	    			if(u.getNome().equals(nomeU))
	    			{
	    				u.setQuantidade(1);//devemos altera em um futuro breve
	    				
	    				if(sala.addUtensilio(u))
	    					addMessage("Atualizado:", "Utensilio: "+u.getNome()+".");
	    				else
	    					addMessage("ERRO:", "Utensilio ja cadastrado.");
	    			}
	    		}
	    	}
	    	else if(itemSelecionado.equals("Categoria"))
	    	{
    			for(Categoria c : categorias)
    			{
    				if(c.getDescricao().equals(nomeC))
    				{
    					sala.setCategoria(c);
    					addMessage("Atualizado:", "Categoria: "+c.getDescricao()+".");
    				}
    			}
    		}else if(itemSelecionado.equals("Tamanho"))
			{
				sala.setTamanhoMax(new Integer(tamanhoInserido));
				addMessage("Atualizado:", "Tamanho: "+tamanhoInserido+".");
			}else
			{
				addMessage("ERRO:", "Tipo inseriro nao encontrado.");
			}
    	}catch(Exception e)
    	{
    		System.out.println(e.getMessage());
    	}
    }
		
	public void removerUtensilio()
	{
		try{
			if(utensilioSelecionadoRemover == null)
			{
				addMessage("ERRO:", "Nao foi possivel remover ou utensilio não encontrado.");
			}else
			{
				if(sala.removerUtensilio(utensilioSelecionadoRemover))
				{
					addMessage("SUCESSO:", "Utensilio: "+utensilioSelecionadoRemover.getNome()+" removido.");
				}
			}
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
		}

	}
	
	public void onItemChange()
	{		
		addMessage("MUDOU", "SIM");
	}
	
    public void addMessage(String summary, String detail) {
    	FacesMessage message;
    	
    	if(summary.equals("ERRO:"))
    	{
    		message = new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail);
    	}else
    	{
    		message = new FacesMessage(summary, detail);
    	}
        
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
	//Setter necessario para a anotação @ManagedProperty Funcionar corretamente =S
		public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

    public UploadedFile getFile(){
        return file;
    }
 
    public void setFile(UploadedFile file){
        this.file = file;
    }
    
	public String getTamanhoInserido() {
		return tamanhoInserido;
	}

	public void setTamanhoInserido(String tamanhoInserido) {
		this.tamanhoInserido = tamanhoInserido;
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
         
    public Categoria getCategoriaSelecionada() {
		return categoriaSelecionada;
	}

	public void setCategoriaSelecionada(Categoria categoriaSelecionada) {
		this.categoriaSelecionada = categoriaSelecionada;
	}

}
