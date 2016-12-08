package Controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

import model.Agenda;

 
@ManagedBean(name="agendaBean")
@SessionScoped
public class AgendaBean implements Serializable {
 

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2829079801620183154L;
	private List<Agenda> agendas;
	private Agenda agendaAtual;
    private String email = "";
    
    @ManagedProperty(value = "#{usuario}")
    private Usuario usuario; 
    
    @PostConstruct
    public void init() {
    	agendas = usuario.getAgendas();
    }
    
    public String getEmail() {
		return email;
	}

	public void setEmail(String email) throws Exception 
	{
		if (!email.equals(""))
		{
			agendaAtual.adicionarNovoParticipante(email);
			this.email = email;
		}
	}
	
	public void atualizarAgendas()
	{
		agendas = usuario.getAgendas();
	}

	public Agenda getAgendaAtual() {
		return agendaAtual;
	}

	public void setAgendaAtual(Agenda agendaAtual) {
		this.agendaAtual = agendaAtual;
	}
     
    public List<Agenda> getAgendas() {
        return agendas;
    }
 
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
	public void confirmar(Agenda agenda) throws Exception {
		boolean lStatus = agenda.getStatus();
		agenda.salvarStatus();
		String mensagem = lStatus ? "Confirmado!" : "Não confirmado!";
        addMessage("Status:", mensagem);
    }
	
	public void cancelar(Agenda agenda) throws Exception {
		agenda.delete();
		if(this.agendas.remove(agenda))
		{
			addMessage("Confirmação:", "Agenda deletada com sucesso!");
		}
		else
		{
			addMessage("Confirmação:", "Agenda não pode ser deletada!");
		}
	} 
    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public Agenda getAgendaById(int id)
    {
    	return agendas.get(id);
    }
    
    public void guardaAgenda(Agenda agenda)
    {
    	this.agendaAtual = agenda;
    	try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("participantes.jsf");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void resetEmail()
    {
    	RequestContext.getCurrentInstance().reset("email");
    }
}
