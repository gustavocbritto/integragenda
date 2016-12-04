package model;
 
import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.SlideEndEvent;
import persistence.CategoriaDAO;
import persistence.SalaDAO;
import persistence.UtensilioDAO;

 
@ManagedBean(name="controleSalasBean")
@SessionScoped
public class ControleSalasBean implements Serializable {
     
	/**
	 * 
	 */
	private static final long serialVersionUID = 6998096403529389893L;
	private UtensilioDAO utensilioDAO = new UtensilioDAO();
	private CategoriaDAO categoriaDAO = new CategoriaDAO();
	private SalaDAO salaDAO = new SalaDAO();
	
	private List<Sala> salas;
     
    private Sala salaSelecionada;
    
    //Variavel para pegar a categoria inserida no filtro
    private Categoria categoriaFiltro;
    //Lista com os utensilios que a pessoa escolheu
    private ArrayList<Utensilio> utensiliosFiltro = new ArrayList<Utensilio>();
    //Lista com todos os utensilios disponiveis para filtragem
    private ArrayList<Utensilio> utensilios =  new ArrayList<Utensilio>();
    //Lista com todas as categorias disponiveis para filtragem
    private ArrayList<Categoria> categorias = new ArrayList<Categoria>();
    //Variavel para pegar o utensilio selecionado para remover da lista do filtro
    private Utensilio utensilioSelecionadoRemover;
    //Variavel para pegar o tamanho da sala do filtro
    private int tamanhoInserido;
    //Variavel para saber o tipo do item que ele quer adicionar ao filtro
    private String itemSelecionado;
    //Lista que guarda os tipos de itens que temos disponivel:
    private List<String> listaItens = new ArrayList<String>();
    //Variaveis para pegar o nome do item escolhido no dropdown
    private String nomeU, nomeC, nomeT;
    //Variavel que infica a cidade que o usuario procura
    private String cidadeSelecionada = "";
    
    //Variveis para filtrar as datas que o usuario quer filtrar a busca
    private Date dt_inicial;
    private Date dt_final;
    
    @ManagedProperty(value = "#{usuario}")
    private Usuario usuario; 
    
    @PostConstruct
    public void init() {
    	
		try {
			
			listaItens.add("Categoria");
			listaItens.add("Utensilio");
			listaItens.add("Tamanho");
			

			selecionaSalas();
			
    		utensilios = utensilioDAO.getLista();
    		categorias = categoriaDAO.getLista();
    		
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public String dataInicialFormatada()
    {
    	DateFormat dtOutput = new SimpleDateFormat("dd/MM/YYYY");
        return dtOutput.format(dt_inicial.getTime());
    }
    
    public String dataFinalFormatada()
    {
    	DateFormat dtOutput = new SimpleDateFormat("dd/MM/YYYY");
        return dtOutput.format(dt_final.getTime());
    }
    
    public void carregarSalas() throws Exception
    {
    	selecionaSalas();
    }
    public void buscaCidadeEData() throws Exception
    {
    	selecionaSalas();
    }
    

    public void buscaCidade() throws Exception
    {
    	selecionaSalas();
    	FacesContext.getCurrentInstance().getExternalContext().redirect("selecaoSala.jsf");
    }

    public void adicionarFiltro()
    {
    	try
    	{
	    	if (itemSelecionado.equals("Utensilio"))
	    	{
	    		Utensilio Uadd = null;
	    		boolean achou = false;
	    		
	    		for(Utensilio u : utensilios)
	    		{
	    			if(u.getNome().equals(nomeU))
	    			{
	    				Uadd = u;
	    				achou = true;
	    			}
	    		}
	    		
	    		if(achou)
	    		{
		    		for(Utensilio u : utensiliosFiltro)
		    		{
		    			if(u.getNome().equals(nomeU))
		    			{
		    				achou = false;
		    			}
		    		}
		    		
		    		
	    		}
	    		if(achou)
	    			utensiliosFiltro.add(Uadd);
	    		
	    			
	    	}
	    	else if(itemSelecionado.equals("Categoria"))
	    	{
	    		Categoria CAdd = null;
	    		boolean achou = false;
	    		for(Categoria c : categorias)
	    		{
	    			if(c.getDescricao().equals(nomeC))
	    			{
	    				CAdd = c;
	    				achou = true;
	    			}
	    		}
	    		if(achou)
	    			categoriaFiltro = CAdd;
	    		
    		}
	    	
	    	selecionaSalas();
    	}catch(Exception e)
    	{
    		System.out.println(e.getMessage());
    	}
    }
    
    public void selecionaSalas() throws Exception
    {
    	salas = salaDAO.consultar();
    	ArrayList<Sala> salaTemp = new ArrayList<Sala>();
    	
    	for(Sala s : salas)
    	{
    		salaTemp.add(s);
    	}
    	
    	
    	if(categoriaFiltro != null)
    	{
    		for(Sala s : salas)
    		{
    			if(!s.getCategoria().getDescricao().equals(categoriaFiltro.getDescricao()))
    			{
    				salaTemp.remove(s);
    			}
    		}
    	}
    	
    	if(tamanhoInserido > 0)
    	{
    		for(Sala s : salas)
    		{
    			if(s.getTamanhoMax() < tamanhoInserido)
    			{
    				salaTemp.remove(s);
    			}
    		}
    	}

    	if(utensiliosFiltro != null)
    	{
    		if(utensiliosFiltro.size() > 0)
    		{
    			for(Sala s : salas)
    			{
    				int cont = 0;
    				for(Utensilio filtro : utensiliosFiltro)
    				{
    					if(s.getUtensilios() != null)
    					{
    						for(Utensilio salaUtensilio : s.getUtensilios())
    						{
		    					if(salaUtensilio.getNome().equals(filtro.getNome()))
		    					{
		    						cont++;
		    					}
	    					}
    					}
    				}
    				
    				if(cont < utensiliosFiltro.size())
    				{
    					salaTemp.remove(s);
    				}
    				
    				
    			}
    		}
    	}
    	
    	if(!cidadeSelecionada.equals(""))
    	{
    		for(Sala s : salas)
    		{
    			if(!s.getLocalizacao().getCidade().equals(cidadeSelecionada))
    			{
    				salaTemp.remove(s);
    			}
    		}
    	}
    	
    	if(dt_inicial != null && dt_final != null)
    	{
    		for(Sala s: salas)
    		{
    			if(!s.getDisponivel(dt_inicial, dt_final))
    			{
    				salaTemp.remove(s);
    			}
    		}
    	}
    	
    	
    	salas.clear();
    	for(Sala s : salaTemp)
    	{
    		salas.add(s);
    	}
    	

    	
    }
    
    public void onSlideEnd(SlideEndEvent event) {
    	tamanhoInserido = new Integer(event.getValue());
        FacesMessage message = new FacesMessage("Sucesso:", "Filtro adicionado. Tamanho: " + event.getValue());
        FacesContext.getCurrentInstance().addMessage(null, message);
    } 
    
    public void removerUtensilio()
    {
		try{
			if(utensilioSelecionadoRemover == null)
			{
				addMessage("ERRO:", "Nao foi possivel remover ou utensilio não encontrado.");
			}else
			{
				if(utensiliosFiltro.remove(utensilioSelecionadoRemover))
				{
					addMessage("SUCESSO:", "Utensilio: "+utensilioSelecionadoRemover.getNome()+" removido.");
				}
			}
			selecionaSalas();
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
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
    
    public void exibirSala() throws IOException
    {
    	FacesContext.getCurrentInstance().getExternalContext().redirect("salaSelecionada.jsf");
    }
    
	//Setter necessario para a anotação @ManagedProperty Funcionar corretamente =S
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
    
    public String getCidadeSelecionada() {
		return cidadeSelecionada;
	}

	public void setCidadeSelecionada(String cidadeSelecionada) {
		this.cidadeSelecionada = cidadeSelecionada;
	}

	public Date getDt_inicial() {
		return dt_inicial;
	}

	public void setDt_inicial(Date dt_inicial) {
		this.dt_inicial = dt_inicial;
	}

	public Date getDt_final() {
		return dt_final;
	}

	public void setDt_final(Date dt_final) {
		this.dt_final = dt_final;
	}

	public void removerTamanho() throws Exception
    {
    	tamanhoInserido = 0;
    	selecionaSalas();
    }
    
    public void removerCategoria() throws Exception
    {
    	categoriaFiltro = null;
    	selecionaSalas();
    }
    public ArrayList<Utensilio> getUtensiliosFiltro() {
		return utensiliosFiltro;
	}

	public void setUtensiliosFiltro(ArrayList<Utensilio> utensiliosFiltro) {
		this.utensiliosFiltro = utensiliosFiltro;
	}

	public String getNomeU() {
		return nomeU;
	}

	public void setNomeU(String nomeU) {
		this.nomeU = nomeU;
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

	public Categoria getCategoriaFiltro() {
		return categoriaFiltro;
	}

	public void setCategoriaFiltro(Categoria categoriaFiltro) {
		this.categoriaFiltro = categoriaFiltro;
	}

	public ArrayList<Utensilio> getUtensilios() {
		return utensilios;
	}

	public void setUtensilios(ArrayList<Utensilio> utensilios) {
		this.utensilios = utensilios;
	}

	public ArrayList<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(ArrayList<Categoria> categorias) {
		this.categorias = categorias;
	}

	public Utensilio getUtensilioSelecionadoRemover() {
		return utensilioSelecionadoRemover;
	}

	public void setUtensilioSelecionadoRemover(Utensilio utensilioSelecionadoRemover) {
		this.utensilioSelecionadoRemover = utensilioSelecionadoRemover;
	}

	public int getTamanhoInserido() {
		return tamanhoInserido;
	}

	public void setTamanhoInserido(int tamanhoInserido) {
		this.tamanhoInserido = tamanhoInserido;
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

	public void setSalas(List<Sala> salas) {
        this.salas = salas;
    }
 
    public List<Sala> getSalas() {
        return salas;
    }
 
 
    public Sala getSalaSelecionada() {
        return salaSelecionada;
    }
 
    public void setSalaSelecionada(Sala salaSelecionada) {
        this.salaSelecionada = salaSelecionada;
    }

	public boolean verificaDisponibilidadeSala(Sala sala) throws Exception 
	{
		boolean retorno =  false;
		
    	if(dt_inicial != null && dt_final != null)
    	{
			if(sala.getDisponivel(dt_inicial, dt_final))
			{
				retorno =  true;
			}
    	}
		return retorno;
	}
}
