package Controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import model.Categoria;
import model.Imagem;
import model.Sala;
import model.Utensilio;
import persistence.CategoriaDAO;
import persistence.SalaDAO;
import persistence.UtensilioDAO;

 
@ManagedBean(name="editSalaBean")
@ViewScoped
//ViewScoped
public class editSalaBean implements Serializable {


	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4308416125678672757L;
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
    private Date horarioInicio;
    private Date horarioFim;
    
    @ManagedProperty(value = "#{usuario}")
    private Usuario usuario; 
	
    @ManagedProperty(value = "#{controleSalasBean}")
    private ControleSalasBean controleSalasBean;
    
    @ManagedProperty(value = "#{controleMinhasSalasBean}")
    private ControleMinhasSalasBean controleMinhasSalasBean;
    
    @PostConstruct
    public void init() {
		//Inicialmente o id da sala eh 0, depois tento pegar o ID real da sala que o usuario quer editar.
    	int idSala = 0;
    	if(controleSalasBean != null)
    		if(controleSalasBean.getSalaSelecionada() != null)
    			idSala = controleSalasBean.getSalaSelecionada().getIdSala();
    	
    	if(controleMinhasSalasBean != null)
    		if(controleMinhasSalasBean.getSalaSelecionada() != null)
    			idSala = controleMinhasSalasBean.getSalaSelecionada().getIdSala();

    	controleSalasBean.setSalaSelecionada(null);
    	controleMinhasSalasBean.setSalaSelecionada(null);
    	
    	//Crio os objetios para conversa com o banco
		SalaDAO salaDAO = new SalaDAO();
		UtensilioDAO utensilioDAO = new UtensilioDAO();
		CategoriaDAO categoriaDAO = new CategoriaDAO();
		
		//Adiciono os itens que o usuario pode querer adicionar na sala
		listaItens = new ArrayList<String>();
		listaItens.add("Categoria");
		listaItens.add("Utensilio");
		listaItens.add("Tamanho");
		
		try 
    	{
			// Se o Id for 0, quer dizer que é sala nova e nao eh pra tentar pegar do banco.
			if(idSala > 0)
			{
				sala = salaDAO.consulta(idSala);
			}
			else
			{
				sala = getInstanceSalaNova();
			}
			
			//Pego as lista de categorias e utensilios disponiveis no banco.
    		utensilios = utensilioDAO.getLista();
    		categorias = categoriaDAO.getLista();
		} 
    	catch (Exception e) 
    	{
    		addMessage("ERROR:", e.getMessage());
			System.out.println(e.getMessage());
		}
    }
    
    public Sala getInstanceSalaNova()
    {
    	Sala salaCriacao = new Sala();
    	
    	Imagem imagemCriacao = new Imagem("/resources/img/novasala.png");
    	salaCriacao.getImagens().add(imagemCriacao);
    	salaCriacao.setPessoa(usuario.getPessoa());
    	
    	return salaCriacao;
    }
    
    public void deletarSala()
    {
    	try
    	{
    		if(sala.podeDeletar())
    		{
    			sala.deletar();
    			addMessage("SUCESSO:", "Sala deletada com sucesso!");
    			FacesContext.getCurrentInstance().getExternalContext().redirect("minhasSalas.jsf");
    		}else
    		{
    			addMessage("ERRO:", "Todos os campos são obrigatorios! Exceto Numero Sala.");
    		}

    	}
    	catch(Exception e)
    	{
    		System.out.println(e.getMessage());
    		addMessage("ERRO:", "Nao foi possivel deletar a sala!");
    	}
    }
    
    public void alugarSala() 
    {	
    	try{
    	//Verifica se o usuario esta logado.
    	if(!usuario.getNomeAtual().equals("Usuario"))
    	{
        	//Verifica se a data esta OK.
    		if(controleSalasBean.verificaDisponibilidadeSala(sala))
    		{
            	//Cria a agenda para esta sala.
    			usuario.criarAgenda(sala, horarioInicio, horarioFim, controleSalasBean.getDt_inicial(), controleSalasBean.getDt_final());
    			
            	//Redirecionar para minha agenda.
    			FacesContext.getCurrentInstance().getExternalContext().redirect("minhaAgenda.jsf");
    		}
    		else
    		{
    			addMessage("ERRO:", "Sala não está diponivel nesta data.");
    		}

    	}else
    	{
    		addMessage("ERRO:", "Usuario deve estar logado.");
    	}
    	}
    	catch(Exception e)
    	{
    		System.out.println(e.getMessage());
    	}

    }
    
    public void voltarSelecaoSala() throws IOException
    {
    	FacesContext.getCurrentInstance().getExternalContext().redirect("selecaoSala.jsf");
    }
    
    public void upload(FileUploadEvent event) {
    	
    	file = event.getFile();
    	try{
    		//file = event.getFile();
        if(file != null) {
        	FacesContext aFacesContext = FacesContext.getCurrentInstance();
        	ServletContext context = (ServletContext) aFacesContext.getExternalContext().getContext(); 
        	String caminhoBase = "//resources//img//";
        	String realPath =  context.getRealPath(caminhoBase);
        	//System.out.println(context.getContextPath());
        	byte[] arquivo = file.getContents();
        	String caminho = realPath + file.getFileName();
            FileOutputStream fos = new FileOutputStream(caminho);
            System.out.println(caminho);
            fos.write(arquivo);
            fos.close();
            Imagem imagem = new Imagem(caminhoBase+file.getFileName());
            imagem.inserir();
            if(sala.getIdSala() > 0)
            {
            	sala.associarImagem(imagem);
            }
            else
            {
            	sala.getImagens().clear();
            	sala.getImagens().add(imagem);
            }
            addMessage("Succesful", file.getFileName() + " is uploaded.");
        }else
        {
        	 addMessage("ERRO:", "Nenhuma imagem selecionada.");
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
    		if(sala.podeSalvar())
    		{
    			sala.salvar();
    			addMessage("SUCESSO:", "Sala salva com sucesso!");
    		}else
    		{
    			addMessage("ERRO:", "Todos os campos são obrigatorios! Exceto Numero Sala.");
    		}

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
		
	//Setter necessario para a anotação @ManagedProperty Funcionar corretamente =S
	public void setControleSalasBean(ControleSalasBean controleSalasBean) {
		this.controleSalasBean = controleSalasBean;
	}
	
	//Setter necessario para a anotação @ManagedProperty Funcionar corretamente =S
	public void setControleMinhasSalasBean(ControleMinhasSalasBean controleMinhasSalasBean) {
		this.controleMinhasSalasBean = controleMinhasSalasBean;
	}
	
	
	
    public Date getHorarioInicio() {
		return horarioInicio;
	}

	public void setHorarioInicio(Date horarioInicio) {
		this.horarioInicio = horarioInicio;
	}

	public Date getHorarioFim() {
		return horarioFim;
	}

	public void setHorarioFim(Date horarioFim) {
		this.horarioFim = horarioFim;
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
